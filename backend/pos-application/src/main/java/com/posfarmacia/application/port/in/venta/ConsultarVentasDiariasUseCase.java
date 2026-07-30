package com.posfarmacia.application.port.in.venta;

import com.posfarmacia.application.dto.venta.ConsultarVentasDiariasQuery;
import com.posfarmacia.application.dto.venta.VentaResult;
import java.util.List;

/** Puerto de entrada RF17: lista ventas filtradas por fecha, caja, usuario y/o cliente. */
public interface ConsultarVentasDiariasUseCase {

    List<VentaResult> consultar(ConsultarVentasDiariasQuery query);
}
