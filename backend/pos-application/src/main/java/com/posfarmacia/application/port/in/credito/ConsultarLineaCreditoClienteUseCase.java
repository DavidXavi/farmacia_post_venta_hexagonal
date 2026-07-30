package com.posfarmacia.application.port.in.credito;

import com.posfarmacia.domain.model.credito.LineaCredito;
import java.util.Optional;
import java.util.UUID;

/** Consulta la linea de credito de un cliente (endpoint GET /clientes/{id}/linea-credito). */
public interface ConsultarLineaCreditoClienteUseCase {

    Optional<LineaCredito> consultarPorCliente(UUID clienteId);
}
