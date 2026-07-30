package com.posfarmacia.application.port.in.venta;

import com.posfarmacia.application.dto.venta.IniciarVentaCommand;
import com.posfarmacia.application.dto.venta.VentaResult;

/** Puerto de entrada RF05/RN01: inicia una venta, exigiendo que la caja este abierta. */
public interface IniciarVentaUseCase {

    VentaResult iniciar(IniciarVentaCommand command);
}
