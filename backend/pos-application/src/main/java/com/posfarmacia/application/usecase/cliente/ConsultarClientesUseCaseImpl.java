package com.posfarmacia.application.usecase.cliente;

import com.posfarmacia.application.port.in.cliente.ConsultarClientesUseCase;
import com.posfarmacia.application.port.out.cliente.ClienteRepositoryPort;
import com.posfarmacia.domain.model.cliente.Cliente;
import java.util.List;

public class ConsultarClientesUseCaseImpl implements ConsultarClientesUseCase {

    private final ClienteRepositoryPort clienteRepositoryPort;

    public ConsultarClientesUseCaseImpl(ClienteRepositoryPort clienteRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
    }

    @Override
    public List<Cliente> consultarTodos() {
        return clienteRepositoryPort.buscarTodos();
    }
}
