package com.posfarmacia.bootstrap.configuration.cliente;

import com.posfarmacia.application.port.in.cliente.ActualizarClienteUseCase;
import com.posfarmacia.application.port.in.cliente.ConsultarClientesUseCase;
import com.posfarmacia.application.port.in.cliente.ConsultarConveniosClienteUseCase;
import com.posfarmacia.application.port.in.cliente.IdentificarClienteUseCase;
import com.posfarmacia.application.port.in.cliente.RegistrarClienteUseCase;
import com.posfarmacia.application.port.in.credito.ConsultarLineaCreditoClienteUseCase;
import com.posfarmacia.application.port.in.credito.RegistrarLineaCreditoUseCase;
import com.posfarmacia.application.port.in.credito.ValidarLineaCreditoUseCase;
import com.posfarmacia.application.port.in.seguro.CalcularCopagoUseCase;
import com.posfarmacia.application.port.in.seguro.ConsultarCoberturaSeguroUseCase;
import com.posfarmacia.application.port.in.seguro.GestionarConvenioUseCase;
import com.posfarmacia.application.port.in.seguro.RegistrarAfiliacionUseCase;
import com.posfarmacia.application.port.out.ClockPort;
import com.posfarmacia.application.port.out.cliente.ClienteCentralPort;
import com.posfarmacia.application.port.out.cliente.ClienteRepositoryPort;
import com.posfarmacia.application.port.out.credito.LineaCreditoRepositoryPort;
import com.posfarmacia.application.port.out.seguro.AfiliacionClienteRepositoryPort;
import com.posfarmacia.application.port.out.seguro.ConvenioSeguroRepositoryPort;
import com.posfarmacia.application.port.out.seguro.SeguroCentralPort;
import com.posfarmacia.application.usecase.cliente.ActualizarClienteUseCaseImpl;
import com.posfarmacia.application.usecase.cliente.ConsultarClientesUseCaseImpl;
import com.posfarmacia.application.usecase.cliente.ConsultarConveniosClienteUseCaseImpl;
import com.posfarmacia.application.usecase.cliente.IdentificarClienteUseCaseImpl;
import com.posfarmacia.application.usecase.cliente.RegistrarClienteUseCaseImpl;
import com.posfarmacia.application.usecase.credito.ConsultarLineaCreditoClienteUseCaseImpl;
import com.posfarmacia.application.usecase.credito.RegistrarLineaCreditoUseCaseImpl;
import com.posfarmacia.application.usecase.credito.ValidarLineaCreditoUseCaseImpl;
import com.posfarmacia.application.usecase.seguro.CalcularCopagoUseCaseImpl;
import com.posfarmacia.application.usecase.seguro.ConsultarCoberturaSeguroUseCaseImpl;
import com.posfarmacia.application.usecase.seguro.GestionarConvenioUseCaseImpl;
import com.posfarmacia.application.usecase.seguro.RegistrarAfiliacionUseCaseImpl;
import com.posfarmacia.domain.service.credito.ValidadorLineaCredito;
import com.posfarmacia.domain.service.seguro.CalculadorCopago;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Cablea los casos de uso de Clientes, Seguros/Convenios y Credito con sus adaptadores de puertos de salida. */
@Configuration
public class ClienteSeguroCreditoUseCaseConfiguration {

    @Bean
    public CalculadorCopago calculadorCopago() {
        return new CalculadorCopago();
    }

    @Bean
    public ValidadorLineaCredito validadorLineaCredito() {
        return new ValidadorLineaCredito();
    }

    @Bean
    public RegistrarClienteUseCase registrarClienteUseCase(ClienteRepositoryPort clientes, ClienteCentralPort clienteCentral) {
        return new RegistrarClienteUseCaseImpl(clientes, clienteCentral);
    }

    @Bean
    public ActualizarClienteUseCase actualizarClienteUseCase(ClienteRepositoryPort clientes) {
        return new ActualizarClienteUseCaseImpl(clientes);
    }

    @Bean
    public ConsultarClientesUseCase consultarClientesUseCase(ClienteRepositoryPort clientes) {
        return new ConsultarClientesUseCaseImpl(clientes);
    }

    @Bean
    public IdentificarClienteUseCase identificarClienteUseCase(ClienteRepositoryPort clientes) {
        return new IdentificarClienteUseCaseImpl(clientes);
    }

    @Bean
    public ConsultarConveniosClienteUseCase consultarConveniosClienteUseCase(
            ClienteRepositoryPort clientes, AfiliacionClienteRepositoryPort afiliaciones) {
        return new ConsultarConveniosClienteUseCaseImpl(clientes, afiliaciones);
    }

    @Bean
    public GestionarConvenioUseCase gestionarConvenioUseCase(ConvenioSeguroRepositoryPort convenios) {
        return new GestionarConvenioUseCaseImpl(convenios);
    }

    @Bean
    public RegistrarAfiliacionUseCase registrarAfiliacionUseCase(ClienteRepositoryPort clientes,
            ConvenioSeguroRepositoryPort convenios, AfiliacionClienteRepositoryPort afiliaciones) {
        return new RegistrarAfiliacionUseCaseImpl(clientes, convenios, afiliaciones);
    }

    @Bean
    public ConsultarCoberturaSeguroUseCase consultarCoberturaSeguroUseCase(ClienteRepositoryPort clientes,
            SeguroCentralPort seguroCentral, CalculadorCopago calculadorCopago, ClockPort clock) {
        return new ConsultarCoberturaSeguroUseCaseImpl(clientes, seguroCentral, calculadorCopago, clock);
    }

    @Bean
    public CalcularCopagoUseCase calcularCopagoUseCase(CalculadorCopago calculadorCopago) {
        return new CalcularCopagoUseCaseImpl(calculadorCopago);
    }

    @Bean
    public RegistrarLineaCreditoUseCase registrarLineaCreditoUseCase(
            ClienteRepositoryPort clientes, LineaCreditoRepositoryPort lineasCredito) {
        return new RegistrarLineaCreditoUseCaseImpl(clientes, lineasCredito);
    }

    @Bean
    public ConsultarLineaCreditoClienteUseCase consultarLineaCreditoClienteUseCase(
            ClienteRepositoryPort clientes, LineaCreditoRepositoryPort lineasCredito) {
        return new ConsultarLineaCreditoClienteUseCaseImpl(clientes, lineasCredito);
    }

    @Bean
    public ValidarLineaCreditoUseCase validarLineaCreditoUseCase(ClienteRepositoryPort clientes,
            LineaCreditoRepositoryPort lineasCredito, ValidadorLineaCredito validador, ClockPort clock) {
        return new ValidarLineaCreditoUseCaseImpl(clientes, lineasCredito, validador, clock);
    }
}
