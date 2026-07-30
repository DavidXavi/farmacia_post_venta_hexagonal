package com.posfarmacia.application.port.in.venta;

import com.posfarmacia.application.dto.venta.SeleccionarPromocionVentaCommand;
import com.posfarmacia.application.dto.venta.VentaResult;

/** Puerto de entrada RN07-RN12: registra la promocion elegida por el cajero para una linea de venta. */
public interface SeleccionarPromocionVentaUseCase {

    VentaResult seleccionar(SeleccionarPromocionVentaCommand command);
}
