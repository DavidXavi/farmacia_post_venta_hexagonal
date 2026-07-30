package com.posfarmacia.application.usecase.seguro;

import com.posfarmacia.application.dto.seguro.RegistrarAfiliacionCommand;
import com.posfarmacia.application.port.in.seguro.RegistrarAfiliacionUseCase;
import com.posfarmacia.application.port.out.cliente.ClienteRepositoryPort;
import com.posfarmacia.application.port.out.seguro.AfiliacionClienteRepositoryPort;
import com.posfarmacia.application.port.out.seguro.ConvenioSeguroRepositoryPort;
import com.posfarmacia.domain.exception.EntidadNoEncontradaException;
import com.posfarmacia.domain.model.seguro.AfiliacionCliente;
import org.springframework.transaction.annotation.Transactional;

public class RegistrarAfiliacionUseCaseImpl implements RegistrarAfiliacionUseCase {

    private final ClienteRepositoryPort clienteRepositoryPort;
    private final ConvenioSeguroRepositoryPort convenioSeguroRepositoryPort;
    private final AfiliacionClienteRepositoryPort afiliacionClienteRepositoryPort;

    public RegistrarAfiliacionUseCaseImpl(ClienteRepositoryPort clienteRepositoryPort,
                                           ConvenioSeguroRepositoryPort convenioSeguroRepositoryPort,
                                           AfiliacionClienteRepositoryPort afiliacionClienteRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
        this.convenioSeguroRepositoryPort = convenioSeguroRepositoryPort;
        this.afiliacionClienteRepositoryPort = afiliacionClienteRepositoryPort;
    }

    @Override
    @Transactional
    public AfiliacionCliente registrar(RegistrarAfiliacionCommand command) {
        clienteRepositoryPort.buscarPorId(command.clienteId())
                .orElseThrow(() -> new EntidadNoEncontradaException("El cliente indicado no existe."));
        convenioSeguroRepositoryPort.buscarPorId(command.convenioId())
                .orElseThrow(() -> new EntidadNoEncontradaException("El convenio indicado no existe."));

        AfiliacionCliente afiliacion = new AfiliacionCliente(command.clienteId(), command.convenioId(),
                command.vigenciaInicio(), command.vigenciaFin());
        return afiliacionClienteRepositoryPort.guardar(afiliacion);
    }
}
