package com.posfarmacia.adapter.in.rest.request.venta;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

/** RN07-RN12: promocion elegida por el cajero para una linea de venta. */
public record SeleccionarPromocionRequest(@NotNull UUID promocionId) {
}
