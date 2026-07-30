package com.posfarmacia.application.port.in.inventario;

import com.posfarmacia.application.dto.inventario.StockVendibleResult;
import java.util.UUID;

/** Puerto de entrada: consulta el stock vendible de un producto en un local (RF14). */
public interface ConsultarStockVendibleUseCase {

    StockVendibleResult consultar(UUID productoId, UUID localId);
}
