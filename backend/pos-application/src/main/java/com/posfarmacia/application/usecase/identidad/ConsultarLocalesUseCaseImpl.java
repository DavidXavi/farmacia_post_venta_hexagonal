package com.posfarmacia.application.usecase.identidad;

import com.posfarmacia.application.port.in.identidad.ConsultarLocalesUseCase;
import com.posfarmacia.application.port.out.identidad.LocalRepositoryPort;
import com.posfarmacia.domain.model.identidad.Local;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/** Listado de locales/sedes registrados. */
public class ConsultarLocalesUseCaseImpl implements ConsultarLocalesUseCase {

    private final LocalRepositoryPort locales;

    public ConsultarLocalesUseCaseImpl(LocalRepositoryPort locales) {
        this.locales = locales;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Local> consultar() {
        return locales.listarTodos();
    }
}
