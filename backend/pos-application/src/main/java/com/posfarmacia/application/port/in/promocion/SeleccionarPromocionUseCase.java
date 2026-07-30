package com.posfarmacia.application.port.in.promocion;

import com.posfarmacia.application.dto.promocion.SeleccionarPromocionCommand;
import com.posfarmacia.domain.model.promocion.AplicacionPromocion;

/**
 * Puerto de entrada RN07-RN12: registra la promocion que el cajero eligio para una linea de
 * venta puntual, validando que sea aplicable y que no se repita en el comprobante (RN09).
 */
public interface SeleccionarPromocionUseCase {

    AplicacionPromocion seleccionar(SeleccionarPromocionCommand command);
}
