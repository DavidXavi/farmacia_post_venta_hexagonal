package com.posfarmacia.application.dto.credito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record RegistrarLineaCreditoCommand(UUID clienteId, BigDecimal montoAutorizado, LocalDate vigenciaInicio, LocalDate vigenciaFin) {
}
