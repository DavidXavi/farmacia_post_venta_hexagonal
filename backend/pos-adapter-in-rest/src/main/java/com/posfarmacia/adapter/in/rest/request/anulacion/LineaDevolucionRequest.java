package com.posfarmacia.adapter.in.rest.request.anulacion;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.UUID;

public record LineaDevolucionRequest(@NotNull UUID detalleVentaId, @Positive int cantidad) {
}
