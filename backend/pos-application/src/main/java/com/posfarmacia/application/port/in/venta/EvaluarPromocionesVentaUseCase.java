package com.posfarmacia.application.port.in.venta;

import com.posfarmacia.application.dto.venta.PromocionDisponibleResult;
import java.util.List;
import java.util.UUID;

/** Puerto de entrada RF06: promociones vigentes aplicables a una linea de una venta puntual. */
public interface EvaluarPromocionesVentaUseCase {

    List<PromocionDisponibleResult> evaluar(UUID ventaId, UUID detalleVentaId);
}
