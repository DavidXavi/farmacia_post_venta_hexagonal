package com.posfarmacia.adapter.in.rest.response.inventario;

/** Cuerpo de error minimo para los endpoints de catalogo/inventario (Word, seccion 10). */
public record ErrorResponse(String codigo, String mensaje) {
}
