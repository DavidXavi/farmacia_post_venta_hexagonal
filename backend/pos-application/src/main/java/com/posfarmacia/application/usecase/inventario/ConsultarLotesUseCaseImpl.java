package com.posfarmacia.application.usecase.inventario;

import com.posfarmacia.application.dto.inventario.LoteResult;
import com.posfarmacia.application.port.in.inventario.ConsultarLotesUseCase;
import com.posfarmacia.application.port.out.inventario.LoteRepositoryPort;
import java.util.List;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

/** Caso de uso RF04: lista lotes, opcionalmente filtrados por producto. */
public class ConsultarLotesUseCaseImpl implements ConsultarLotesUseCase {

    private final LoteRepositoryPort lotes;

    public ConsultarLotesUseCaseImpl(LoteRepositoryPort lotes) {
        this.lotes = lotes;
    }

    @Override
    @Transactional(readOnly = true)
    public List<LoteResult> consultar(UUID productoId) {
        return lotes.listar(productoId).stream().map(LoteResultMapper::aResult).toList();
    }
}
