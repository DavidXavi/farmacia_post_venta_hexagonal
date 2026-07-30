package com.posfarmacia.application.port.in.cliente;

import com.posfarmacia.application.dto.cliente.RegistrarClienteCommand;
import com.posfarmacia.domain.model.cliente.Cliente;

/** RF09: registrar los datos basicos de un cliente cuando aun no existe. */
public interface RegistrarClienteUseCase {

    Cliente registrar(RegistrarClienteCommand command);
}
