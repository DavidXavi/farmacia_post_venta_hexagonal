package com.posfarmacia.application.port.in.inventario;

import com.posfarmacia.application.dto.inventario.LoteResult;
import java.util.List;
import java.util.UUID;

/** Puerto de entrada: lista lotes, opcionalmente filtrados por producto (RF04). */
public interface ConsultarLotesUseCase {

    List<LoteResult> consultar(UUID productoId);
}
