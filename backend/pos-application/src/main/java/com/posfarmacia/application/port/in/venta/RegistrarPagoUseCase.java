package com.posfarmacia.application.port.in.venta;

import com.posfarmacia.application.dto.venta.RegistrarPagoCommand;
import com.posfarmacia.application.dto.venta.VentaResult;

/** Puerto de entrada RF12: registra un pago sobre una venta en proceso. */
public interface RegistrarPagoUseCase {

    VentaResult registrar(RegistrarPagoCommand command);
}
