package com.posfarmacia.adapter.in.rest.response.receta;

import java.util.UUID;

public record ValidarRecetaResponse(
        UUID recetaId,
        String numero,
        String tipo,
        String estado,
        boolean retenidaEnBotica,
        boolean usoRegistrado) {
}
