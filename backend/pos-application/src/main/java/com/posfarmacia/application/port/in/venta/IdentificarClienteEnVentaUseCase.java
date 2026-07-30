package com.posfarmacia.application.port.in.venta;

import com.posfarmacia.application.dto.venta.IdentificarClienteCommand;
import com.posfarmacia.application.dto.venta.VentaResult;

/** Puerto de entrada RF09: identifica al cliente de una venta por su DNI. */
public interface IdentificarClienteEnVentaUseCase {

    VentaResult identificar(IdentificarClienteCommand command);
}
