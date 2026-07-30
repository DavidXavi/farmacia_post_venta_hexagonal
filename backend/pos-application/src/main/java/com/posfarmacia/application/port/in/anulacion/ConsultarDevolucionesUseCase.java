package com.posfarmacia.application.port.in.anulacion;

import com.posfarmacia.application.dto.anulacion.DevolucionResult;
import java.util.List;
import java.util.UUID;

/** Puerto de entrada RF16: historial de devoluciones registradas sobre una venta. */
public interface ConsultarDevolucionesUseCase {

    List<DevolucionResult> consultarPorVenta(UUID ventaId);
}
