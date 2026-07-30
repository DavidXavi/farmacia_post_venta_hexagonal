package com.posfarmacia.application.port.in.inventario;

import com.posfarmacia.application.dto.inventario.LoteResult;
import com.posfarmacia.application.dto.inventario.RegistrarLoteCommand;

/** Puerto de entrada: registra el ingreso de un lote de mercaderia (RF04). */
public interface RegistrarIngresoLoteUseCase {

    LoteResult registrar(RegistrarLoteCommand command);
}
