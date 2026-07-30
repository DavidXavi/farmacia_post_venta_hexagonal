package com.posfarmacia.adapter.in.rest.response.inventario;

import com.posfarmacia.application.dto.inventario.InventarioResult;
import java.time.Instant;
import java.util.UUID;

public record InventarioResponse(UUID productoId, UUID localId, int cantidadActual, Instant actualizadoEn) {

    public static InventarioResponse desde(InventarioResult result) {
        return new InventarioResponse(
                result.productoId(), result.localId(), result.cantidadActual(), result.actualizadoEn());
    }
}
