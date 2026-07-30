package com.posfarmacia.adapter.in.rest.response.reporte;

import com.posfarmacia.application.dto.reporte.LoteProximoAVencerResult;
import java.time.LocalDate;
import java.util.UUID;

public record LoteProximoAVencerResponse(
        UUID loteId,
        String codigo,
        UUID productoId,
        LocalDate fechaVencimiento,
        int cantidadDisponible) {

    public static LoteProximoAVencerResponse desde(LoteProximoAVencerResult result) {
        return new LoteProximoAVencerResponse(result.loteId(), result.codigo(), result.productoId(),
                result.fechaVencimiento(), result.cantidadDisponible());
    }
}
