package com.posfarmacia.adapter.in.rest.request.catalogo;

import jakarta.validation.constraints.NotBlank;

/** RF03: alta de categoria o laboratorio, catalogos simples que solo piden un nombre. */
public record NombreRequest(@NotBlank String nombre) {
}
