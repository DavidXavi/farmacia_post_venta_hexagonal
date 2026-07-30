package com.posfarmacia.application.port.in.inventario;

import com.posfarmacia.application.dto.inventario.InventarioResult;
import java.util.List;
import java.util.UUID;

/**
 * Puerto de entrada RF15: consulta del rollup de existencias consolidado por local, usado por
 * InventarioPage.jsx. Es solo lectura: el stock se recalcula siempre desde los lotes, nunca se edita aqui.
 */
public interface ConsultarInventarioUseCase {

    List<InventarioResult> consultarPorLocal(UUID localId);
}
