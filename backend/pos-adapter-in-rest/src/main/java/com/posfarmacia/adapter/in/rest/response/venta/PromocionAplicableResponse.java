package com.posfarmacia.adapter.in.rest.response.venta;

import com.posfarmacia.adapter.in.rest.controller.promocion.TipoBeneficioPromocionTexto;
import com.posfarmacia.application.dto.venta.PromocionDisponibleResult;
import java.math.BigDecimal;
import java.util.UUID;

public record PromocionAplicableResponse(UUID id, String nombre, String tipoBeneficio, BigDecimal valorBeneficio) {

    public static PromocionAplicableResponse desde(PromocionDisponibleResult result) {
        return new PromocionAplicableResponse(result.id(), result.nombre(),
                TipoBeneficioPromocionTexto.desdeEnum(result.tipoBeneficio()), result.valorBeneficio());
    }
}
