package com.posfarmacia.adapter.in.rest.response.catalogo;

import com.posfarmacia.application.dto.catalogo.PresentacionResult;
import java.util.UUID;

public record PresentacionResponse(UUID id, String nombre, String unidadMedida) {

    public static PresentacionResponse desde(PresentacionResult result) {
        return new PresentacionResponse(result.id(), result.nombre(), result.unidadMedida());
    }
}
