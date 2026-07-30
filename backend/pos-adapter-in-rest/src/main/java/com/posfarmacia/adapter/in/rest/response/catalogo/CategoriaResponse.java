package com.posfarmacia.adapter.in.rest.response.catalogo;

import com.posfarmacia.application.dto.catalogo.CategoriaResult;
import java.util.UUID;

public record CategoriaResponse(UUID id, String nombre) {

    public static CategoriaResponse desde(CategoriaResult result) {
        return new CategoriaResponse(result.id(), result.nombre());
    }
}
