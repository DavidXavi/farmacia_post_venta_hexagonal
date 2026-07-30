package com.posfarmacia.adapter.in.rest.request.seguro;

import jakarta.validation.constraints.NotBlank;

public record RegistrarConvenioRequest(@NotBlank String nombre) {
}
