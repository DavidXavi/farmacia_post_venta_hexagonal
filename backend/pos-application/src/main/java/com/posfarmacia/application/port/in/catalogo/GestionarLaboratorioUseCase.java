package com.posfarmacia.application.port.in.catalogo;

import com.posfarmacia.application.dto.catalogo.LaboratorioResult;
import java.util.List;

/** Puerto de entrada: alta y consulta de laboratorios del catalogo (RF03). CRUD simple, sin invariantes. */
public interface GestionarLaboratorioUseCase {

    LaboratorioResult crear(String nombre);

    List<LaboratorioResult> listar();
}
