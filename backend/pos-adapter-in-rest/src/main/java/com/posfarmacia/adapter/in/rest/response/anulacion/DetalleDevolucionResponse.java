package com.posfarmacia.adapter.in.rest.response.anulacion;

import com.posfarmacia.application.dto.anulacion.DetalleDevolucionResult;
import java.math.BigDecimal;
import java.util.UUID;

public record DetalleDevolucionResponse(UUID id, UUID detalleVentaId, UUID productoId, int cantidad,
        BigDecimal montoDevuelto) {

    public static DetalleDevolucionResponse desde(DetalleDevolucionResult result) {
        return new DetalleDevolucionResponse(result.id(), result.detalleVentaId(), result.productoId(),
                result.cantidad(), result.montoDevuelto());
    }
}
