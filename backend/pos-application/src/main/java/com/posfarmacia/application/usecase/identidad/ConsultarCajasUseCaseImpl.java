package com.posfarmacia.application.usecase.identidad;

import com.posfarmacia.application.port.in.identidad.ConsultarCajasUseCase;
import com.posfarmacia.application.port.out.identidad.CajaRepositoryPort;
import com.posfarmacia.domain.model.identidad.Caja;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/** RF02: listado de cajas registradas. */
public class ConsultarCajasUseCaseImpl implements ConsultarCajasUseCase {

    private final CajaRepositoryPort cajas;

    public ConsultarCajasUseCaseImpl(CajaRepositoryPort cajas) {
        this.cajas = cajas;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Caja> consultar() {
        return cajas.listarTodas();
    }
}
