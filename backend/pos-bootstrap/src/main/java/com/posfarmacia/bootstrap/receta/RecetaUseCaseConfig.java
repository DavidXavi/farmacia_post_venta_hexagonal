package com.posfarmacia.bootstrap.receta;

import com.posfarmacia.application.port.in.receta.ConsultarHistorialRecetasUseCase;
import com.posfarmacia.application.port.in.receta.RegistrarRecetaUseCase;
import com.posfarmacia.application.port.in.receta.RevisarRecetaUseCase;
import com.posfarmacia.application.port.in.receta.ValidarRecetaUseCase;
import com.posfarmacia.application.port.out.ClockPort;
import com.posfarmacia.application.port.out.receta.RecetaRepositoryPort;
import com.posfarmacia.application.usecase.receta.ConsultarHistorialRecetasUseCaseImpl;
import com.posfarmacia.application.usecase.receta.RegistrarRecetaUseCaseImpl;
import com.posfarmacia.application.usecase.receta.RevisarRecetaUseCaseImpl;
import com.posfarmacia.application.usecase.receta.ValidarRecetaUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Punto de composicion del contexto de recetas: pos-application no depende de Spring
 * mas alla de spring-tx, asi que los casos de uso se instancian aqui con {@code new} y
 * se exponen como beans, en vez de anotarlos con @Service dentro de pos-application.
 */
@Configuration
public class RecetaUseCaseConfig {

    @Bean
    public ValidarRecetaUseCase validarRecetaUseCase(RecetaRepositoryPort recetaRepositoryPort, ClockPort clockPort) {
        return new ValidarRecetaUseCaseImpl(recetaRepositoryPort, clockPort);
    }

    @Bean
    public ConsultarHistorialRecetasUseCase consultarHistorialRecetasUseCase(
            RecetaRepositoryPort recetaRepositoryPort) {
        return new ConsultarHistorialRecetasUseCaseImpl(recetaRepositoryPort);
    }

    @Bean
    public RegistrarRecetaUseCase registrarRecetaUseCase(RecetaRepositoryPort recetaRepositoryPort) {
        return new RegistrarRecetaUseCaseImpl(recetaRepositoryPort);
    }

    @Bean
    public RevisarRecetaUseCase revisarRecetaUseCase(RecetaRepositoryPort recetaRepositoryPort) {
        return new RevisarRecetaUseCaseImpl(recetaRepositoryPort);
    }
}
