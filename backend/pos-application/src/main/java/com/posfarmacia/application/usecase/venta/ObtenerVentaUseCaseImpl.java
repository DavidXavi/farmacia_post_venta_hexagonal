package com.posfarmacia.application.usecase.venta;

import com.posfarmacia.application.dto.venta.VentaResult;
import com.posfarmacia.application.port.in.venta.ObtenerVentaUseCase;
import com.posfarmacia.application.port.out.inventario.ProductoRepositoryPort;
import com.posfarmacia.application.port.out.venta.VentaRepositoryPort;
import com.posfarmacia.domain.model.venta.Venta;
import java.util.UUID;
import org.springframework.transaction.annotation.Transactional;

/** Caso de uso RF05: consulta una venta por id. */
public class ObtenerVentaUseCaseImpl implements ObtenerVentaUseCase {

    private final VentaRepositoryPort ventas;
    private final ProductoRepositoryPort productos;

    public ObtenerVentaUseCaseImpl(VentaRepositoryPort ventas, ProductoRepositoryPort productos) {
        this.ventas = ventas;
        this.productos = productos;
    }

    @Override
    @Transactional(readOnly = true)
    public VentaResult obtener(UUID ventaId) {
        Venta venta = VentaResultMapper.buscarVentaOLanzar(ventas, ventaId);
        return VentaResultMapper.aResultado(venta, productos);
    }
}
