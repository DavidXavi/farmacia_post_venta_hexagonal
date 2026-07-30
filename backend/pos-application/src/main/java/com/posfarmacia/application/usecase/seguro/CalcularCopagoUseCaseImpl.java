package com.posfarmacia.application.usecase.seguro;

import com.posfarmacia.application.dto.seguro.CalcularCopagoCommand;
import com.posfarmacia.application.port.in.seguro.CalcularCopagoUseCase;
import com.posfarmacia.domain.service.seguro.CalculadorCopago;
import com.posfarmacia.domain.service.seguro.ResultadoCopago;
import com.posfarmacia.domain.valueobject.Dinero;
import com.posfarmacia.domain.valueobject.Porcentaje;

public class CalcularCopagoUseCaseImpl implements CalcularCopagoUseCase {

    private final CalculadorCopago calculadorCopago;

    public CalcularCopagoUseCaseImpl(CalculadorCopago calculadorCopago) {
        this.calculadorCopago = calculadorCopago;
    }

    @Override
    public ResultadoCopago calcular(CalcularCopagoCommand command) {
        Porcentaje porcentaje = command.porcentajeCubierto() == null ? null : new Porcentaje(command.porcentajeCubierto());
        return calculadorCopago.calcular(new Dinero(command.montoLinea()), command.convenioActivo(),
                command.afiliacionActivaYVigente(), porcentaje);
    }
}
