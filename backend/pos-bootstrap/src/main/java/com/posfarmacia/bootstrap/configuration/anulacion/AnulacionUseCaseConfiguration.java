package com.posfarmacia.bootstrap.configuration.anulacion;

import com.posfarmacia.application.port.in.anulacion.ConsultarDevolucionesUseCase;
import com.posfarmacia.application.port.in.anulacion.EmitirNotaCreditoUseCase;
import com.posfarmacia.application.port.in.anulacion.RegistrarDevolucionUseCase;
import com.posfarmacia.application.port.out.ClockPort;
import com.posfarmacia.application.port.out.anulacion.DevolucionRepositoryPort;
import com.posfarmacia.application.port.out.anulacion.NotaCreditoRepositoryPort;
import com.posfarmacia.application.port.out.inventario.ExistenciaLoteRepositoryPort;
import com.posfarmacia.application.port.out.inventario.LoteRepositoryPort;
import com.posfarmacia.application.port.out.inventario.MovimientoInventarioRepositoryPort;
import com.posfarmacia.application.port.out.inventario.ProductoRepositoryPort;
import com.posfarmacia.application.port.out.venta.VentaRepositoryPort;
import com.posfarmacia.application.usecase.anulacion.ConsultarDevolucionesUseCaseImpl;
import com.posfarmacia.application.usecase.anulacion.EmitirNotaCreditoUseCaseImpl;
import com.posfarmacia.application.usecase.anulacion.RegistrarDevolucionUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cablea los casos de uso del contexto Anulaciones (devoluciones/notas de credito), que reutiliza
 * los puertos ya definidos por Ventas (VentaRepositoryPort) e Inventario (Lote/ExistenciaLote/
 * MovimientoInventario/ProductoRepositoryPort) para la reversion de stock (RN42/RN43). Los casos de
 * uso son POJOs de pos-application (sin anotaciones de Spring); pos-bootstrap es el unico modulo
 * que conoce las implementaciones concretas y las conecta mediante @Bean.
 */
@Configuration
public class AnulacionUseCaseConfiguration {

    @Bean
    public RegistrarDevolucionUseCase registrarDevolucionUseCase(VentaRepositoryPort ventas,
            ProductoRepositoryPort productos, DevolucionRepositoryPort devoluciones, LoteRepositoryPort lotes,
            ExistenciaLoteRepositoryPort existencias, MovimientoInventarioRepositoryPort movimientosInventario,
            ClockPort clock) {
        return new RegistrarDevolucionUseCaseImpl(ventas, productos, devoluciones, lotes, existencias,
                movimientosInventario, clock);
    }

    @Bean
    public ConsultarDevolucionesUseCase consultarDevolucionesUseCase(DevolucionRepositoryPort devoluciones) {
        return new ConsultarDevolucionesUseCaseImpl(devoluciones);
    }

    @Bean
    public EmitirNotaCreditoUseCase emitirNotaCreditoUseCase(VentaRepositoryPort ventas,
            NotaCreditoRepositoryPort notasCredito, LoteRepositoryPort lotes,
            ExistenciaLoteRepositoryPort existencias, MovimientoInventarioRepositoryPort movimientosInventario,
            ClockPort clock) {
        return new EmitirNotaCreditoUseCaseImpl(ventas, notasCredito, lotes, existencias, movimientosInventario,
                clock);
    }
}
