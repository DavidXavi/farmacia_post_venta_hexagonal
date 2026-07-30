package com.posfarmacia.adapter.in.rest.request.venta;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

/** RF10: aplica un convenio de seguro a la venta. */
public record AplicarConvenioRequest(@NotNull UUID convenioId) {
}
