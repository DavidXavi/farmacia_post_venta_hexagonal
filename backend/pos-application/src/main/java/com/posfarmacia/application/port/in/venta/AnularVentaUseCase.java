package com.posfarmacia.application.port.in.venta;

import com.posfarmacia.application.dto.venta.VentaResult;
import java.util.UUID;

/**
 * Puerto de entrada RN39/RN40/RN42: anula una venta confirmada del mismo dia y revierte el stock
 * despachado. Si la venta no es del mismo dia, el dominio rechaza la operacion (debe emitirse una
 * nota de credito, contexto que se migra despues).
 */
public interface AnularVentaUseCase {

    VentaResult anular(UUID ventaId);
}
