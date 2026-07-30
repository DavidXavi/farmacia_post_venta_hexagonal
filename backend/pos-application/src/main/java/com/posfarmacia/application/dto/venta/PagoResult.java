package com.posfarmacia.application.dto.venta;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record PagoResult(UUID id, UUID formaPagoId, BigDecimal monto, String codigoAutorizacion, Instant fecha) {
}
