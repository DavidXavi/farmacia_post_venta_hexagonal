package com.posfarmacia.adapter.in.rest.response.venta;

import com.posfarmacia.application.dto.venta.DetalleVentaLoteResult;
import java.util.UUID;

public record DetalleVentaLoteResponse(UUID id, UUID loteId, int cantidadTomada) {

    public static DetalleVentaLoteResponse desde(DetalleVentaLoteResult result) {
        return new DetalleVentaLoteResponse(result.id(), result.loteId(), result.cantidadTomada());
    }
}
