package com.posfarmacia.application.usecase.venta;

import com.posfarmacia.application.dto.venta.ConsultarVentasDiariasQuery;
import com.posfarmacia.application.dto.venta.VentaResult;
import com.posfarmacia.application.port.in.venta.ConsultarVentasDiariasUseCase;
import com.posfarmacia.application.port.out.inventario.ProductoRepositoryPort;
import com.posfarmacia.application.port.out.venta.VentaRepositoryPort;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/** Caso de uso RF17: lista ventas filtradas por fecha, caja, usuario y/o cliente. */
public class ConsultarVentasDiariasUseCaseImpl implements ConsultarVentasDiariasUseCase {

    private final VentaRepositoryPort ventas;
    private final ProductoRepositoryPort productos;

    public ConsultarVentasDiariasUseCaseImpl(VentaRepositoryPort ventas, ProductoRepositoryPort productos) {
        this.ventas = ventas;
        this.productos = productos;
    }

    @Override
    @Transactional(readOnly = true)
    public List<VentaResult> consultar(ConsultarVentasDiariasQuery query) {
        return ventas.buscar(query.fecha(), query.cajaId(), query.usuarioId(), query.clienteId()).stream()
                .map(venta -> VentaResultMapper.aResultado(venta, productos))
                .toList();
    }
}
