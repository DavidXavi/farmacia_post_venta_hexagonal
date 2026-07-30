package com.posfarmacia.adapter.in.rest.controller.identidad;

import com.posfarmacia.adapter.in.rest.response.identidad.LocalResponse;
import com.posfarmacia.application.port.in.identidad.ConsultarLocalesUseCase;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** Consulta de locales/sedes, usada por el frontend al registrar usuarios y cajas. */
@RestController
@RequestMapping("/api/locales")
public class LocalesController {

    private final ConsultarLocalesUseCase consultarLocales;

    public LocalesController(ConsultarLocalesUseCase consultarLocales) {
        this.consultarLocales = consultarLocales;
    }

    @GetMapping
    public List<LocalResponse> listar() {
        return consultarLocales.consultar().stream().map(LocalResponse::desde).toList();
    }
}
