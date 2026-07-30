package com.posfarmacia.application.usecase.inventario;

import com.posfarmacia.application.dto.inventario.LoteResult;
import com.posfarmacia.application.dto.inventario.RegistrarLoteCommand;
import com.posfarmacia.application.port.in.inventario.RegistrarIngresoLoteUseCase;
import com.posfarmacia.application.port.out.ClockPort;
import com.posfarmacia.application.port.out.inventario.ExistenciaLoteRepositoryPort;
import com.posfarmacia.application.port.out.inventario.LoteRepositoryPort;
import com.posfarmacia.application.port.out.inventario.ProductoRepositoryPort;
import com.posfarmacia.domain.exception.EntidadNoEncontradaException;
import com.posfarmacia.domain.model.inventario.ExistenciaLote;
import com.posfarmacia.domain.model.inventario.Lote;
import com.posfarmacia.domain.valueobject.Cantidad;
import com.posfarmacia.domain.valueobject.CodigoLote;
import com.posfarmacia.domain.valueobject.Dinero;
import com.posfarmacia.domain.valueobject.FechaVencimiento;
import org.springframework.transaction.annotation.Transactional;

/**
 * Caso de uso RF04: registra el ingreso de un lote y recalcula el rollup de existencias del producto+local
 * desde la suma de sus lotes (nunca es fuente de verdad, ver {@link ExistenciaLote}).
 */
public class RegistrarIngresoLoteUseCaseImpl implements RegistrarIngresoLoteUseCase {

    private final LoteRepositoryPort lotes;
    private final ProductoRepositoryPort productos;
    private final ExistenciaLoteRepositoryPort existencias;
    private final ClockPort clock;

    public RegistrarIngresoLoteUseCaseImpl(LoteRepositoryPort lotes, ProductoRepositoryPort productos,
            ExistenciaLoteRepositoryPort existencias, ClockPort clock) {
        this.lotes = lotes;
        this.productos = productos;
        this.existencias = existencias;
        this.clock = clock;
    }

    @Override
    @Transactional
    public LoteResult registrar(RegistrarLoteCommand command) {
        productos.buscarPorId(command.productoId())
                .orElseThrow(() -> new EntidadNoEncontradaException("El producto indicado no existe."));

        Lote lote = new Lote(
                new CodigoLote(command.codigo()),
                command.productoId(),
                new FechaVencimiento(command.fechaVencimiento()),
                new Cantidad(command.cantidadRecibida()),
                command.localId(),
                command.costo() == null ? null : new Dinero(command.costo()));

        Lote guardado = lotes.guardar(lote);
        SincronizadorExistencias.sincronizar(command.productoId(), command.localId(), lotes, existencias, clock);
        return LoteResultMapper.aResult(guardado);
    }
}
