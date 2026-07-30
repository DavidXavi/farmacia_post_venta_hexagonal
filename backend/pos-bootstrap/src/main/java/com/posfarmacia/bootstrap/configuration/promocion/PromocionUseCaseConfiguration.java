package com.posfarmacia.bootstrap.configuration.promocion;

import com.posfarmacia.application.port.in.promocion.EvaluarPromocionesUseCase;
import com.posfarmacia.application.port.in.promocion.GestionarPromocionUseCase;
import com.posfarmacia.application.port.in.promocion.SeleccionarPromocionUseCase;
import com.posfarmacia.application.port.out.ClockPort;
import com.posfarmacia.application.port.out.promocion.PromocionRepositoryPort;
import com.posfarmacia.application.usecase.promocion.EvaluarPromocionesUseCaseImpl;
import com.posfarmacia.application.usecase.promocion.GestionarPromocionUseCaseImpl;
import com.posfarmacia.application.usecase.promocion.SeleccionarPromocionUseCaseImpl;
import com.posfarmacia.domain.service.promocion.EvaluadorPromociones;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/** Cablea los casos de uso del contexto Promociones (RF06) con sus adaptadores de puertos de salida. */
@Configuration
public class PromocionUseCaseConfiguration {

    @Bean
    public EvaluadorPromociones evaluadorPromociones() {
        return new EvaluadorPromociones();
    }

    @Bean
    public EvaluarPromocionesUseCase evaluarPromocionesUseCase(
            PromocionRepositoryPort promociones, ClockPort clock, EvaluadorPromociones evaluador) {
        return new EvaluarPromocionesUseCaseImpl(promociones, clock, evaluador);
    }

    @Bean
    public SeleccionarPromocionUseCase seleccionarPromocionUseCase(
            PromocionRepositoryPort promociones, ClockPort clock, EvaluadorPromociones evaluador) {
        return new SeleccionarPromocionUseCaseImpl(promociones, clock, evaluador);
    }

    @Bean
    public GestionarPromocionUseCase gestionarPromocionUseCase(PromocionRepositoryPort promociones) {
        return new GestionarPromocionUseCaseImpl(promociones);
    }
}
