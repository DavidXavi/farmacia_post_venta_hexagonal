package com.posfarmacia.application.port.in.credito;

import com.posfarmacia.application.dto.credito.RegistrarLineaCreditoCommand;
import com.posfarmacia.domain.model.credito.LineaCredito;

/** RF11: registra una nueva linea de credito para un cliente (endpoint POST /api/lineas-credito). */
public interface RegistrarLineaCreditoUseCase {

    LineaCredito registrar(RegistrarLineaCreditoCommand command);
}
