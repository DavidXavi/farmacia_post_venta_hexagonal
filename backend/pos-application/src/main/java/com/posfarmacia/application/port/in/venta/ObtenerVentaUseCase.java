package com.posfarmacia.application.port.in.venta;

import com.posfarmacia.application.dto.venta.VentaResult;
import java.util.UUID;

/** Puerto de entrada RF05: consulta una venta por id. */
public interface ObtenerVentaUseCase {

    VentaResult obtener(UUID ventaId);
}
