package com.posfarmacia.bootstrap.configuration.venta;

import com.posfarmacia.application.port.in.credito.ValidarLineaCreditoUseCase;
import com.posfarmacia.application.port.in.promocion.EvaluarPromocionesUseCase;
import com.posfarmacia.application.port.in.promocion.SeleccionarPromocionUseCase;
import com.posfarmacia.application.port.in.receta.ValidarRecetaUseCase;
import com.posfarmacia.application.port.in.seguro.CalcularCopagoUseCase;
import com.posfarmacia.application.port.in.venta.AgregarProductoAVentaUseCase;
import com.posfarmacia.application.port.in.venta.AnularVentaUseCase;
import com.posfarmacia.application.port.in.venta.AplicarConvenioAVentaUseCase;
import com.posfarmacia.application.port.in.venta.ConfirmarVentaUseCase;
import com.posfarmacia.application.port.in.venta.ConsultarFormasPagoUseCase;
import com.posfarmacia.application.port.in.venta.ConsultarVentasDiariasUseCase;
import com.posfarmacia.application.port.in.venta.EvaluarPromocionesVentaUseCase;
import com.posfarmacia.application.port.in.venta.IdentificarClienteEnVentaUseCase;
import com.posfarmacia.application.port.in.venta.IniciarVentaUseCase;
import com.posfarmacia.application.port.in.venta.ObtenerVentaUseCase;
import com.posfarmacia.application.port.in.venta.RegistrarPagoUseCase;
import com.posfarmacia.application.port.in.venta.SeleccionarPromocionVentaUseCase;
import com.posfarmacia.application.port.out.ClockPort;
import com.posfarmacia.application.port.out.cliente.ClienteRepositoryPort;
import com.posfarmacia.application.port.out.credito.LineaCreditoRepositoryPort;
import com.posfarmacia.application.port.out.credito.MovimientoCreditoRepositoryPort;
import com.posfarmacia.application.port.out.identidad.SesionCajaRepositoryPort;
import com.posfarmacia.application.port.out.inventario.ExistenciaLoteRepositoryPort;
import com.posfarmacia.application.port.out.inventario.LoteRepositoryPort;
import com.posfarmacia.application.port.out.inventario.MovimientoInventarioRepositoryPort;
import com.posfarmacia.application.port.out.inventario.ProductoRepositoryPort;
import com.posfarmacia.application.port.out.seguro.AfiliacionClienteRepositoryPort;
import com.posfarmacia.application.port.out.seguro.ConvenioSeguroRepositoryPort;
import com.posfarmacia.application.port.out.venta.FormaPagoRepositoryPort;
import com.posfarmacia.application.port.out.venta.VentaRepositoryPort;
import com.posfarmacia.application.usecase.venta.AgregarProductoAVentaUseCaseImpl;
import com.posfarmacia.application.usecase.venta.AnularVentaUseCaseImpl;
import com.posfarmacia.application.usecase.venta.AplicarConvenioAVentaUseCaseImpl;
import com.posfarmacia.application.usecase.venta.ConfirmarVentaUseCaseImpl;
import com.posfarmacia.application.usecase.venta.ConsultarFormasPagoUseCaseImpl;
import com.posfarmacia.application.usecase.venta.ConsultarVentasDiariasUseCaseImpl;
import com.posfarmacia.application.usecase.venta.EvaluarPromocionesVentaUseCaseImpl;
import com.posfarmacia.application.usecase.venta.IdentificarClienteEnVentaUseCaseImpl;
import com.posfarmacia.application.usecase.venta.IniciarVentaUseCaseImpl;
import com.posfarmacia.application.usecase.venta.ObtenerVentaUseCaseImpl;
import com.posfarmacia.application.usecase.venta.RegistrarPagoUseCaseImpl;
import com.posfarmacia.application.usecase.venta.SeleccionarPromocionVentaUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cablea los casos de uso del contexto Ventas/Pagos, el orquestador de los 5 contextos ya
 * migrados (identidad/caja, catalogo/inventario, promociones, recetas, clientes/seguros/credito).
 * Los casos de uso son POJOs de pos-application (sin anotaciones de Spring, para que el nucleo
 * sea probable sin framework); pos-bootstrap es el unico modulo que conoce las implementaciones
 * concretas y las conecta mediante @Bean (mismo patron que IdentidadUseCaseConfiguration/RecetaUseCaseConfig).
 */
@Configuration
public class VentaUseCaseConfiguration {

    @Bean
    public IniciarVentaUseCase iniciarVentaUseCase(VentaRepositoryPort ventas, SesionCajaRepositoryPort sesionesCaja,
            ClienteRepositoryPort clientes, ProductoRepositoryPort productos, ClockPort clock) {
        return new IniciarVentaUseCaseImpl(ventas, sesionesCaja, clientes, productos, clock);
    }

    @Bean
    public AgregarProductoAVentaUseCase agregarProductoAVentaUseCase(VentaRepositoryPort ventas,
            ProductoRepositoryPort productos) {
        return new AgregarProductoAVentaUseCaseImpl(ventas, productos);
    }

    @Bean
    public EvaluarPromocionesVentaUseCase evaluarPromocionesVentaUseCase(VentaRepositoryPort ventas,
            EvaluarPromocionesUseCase evaluarPromociones) {
        return new EvaluarPromocionesVentaUseCaseImpl(ventas, evaluarPromociones);
    }

    @Bean
    public SeleccionarPromocionVentaUseCase seleccionarPromocionVentaUseCase(VentaRepositoryPort ventas,
            SeleccionarPromocionUseCase seleccionarPromocion, ProductoRepositoryPort productos) {
        return new SeleccionarPromocionVentaUseCaseImpl(ventas, seleccionarPromocion, productos);
    }

    @Bean
    public IdentificarClienteEnVentaUseCase identificarClienteEnVentaUseCase(VentaRepositoryPort ventas,
            ClienteRepositoryPort clientes, ProductoRepositoryPort productos) {
        return new IdentificarClienteEnVentaUseCaseImpl(ventas, clientes, productos);
    }

    @Bean
    public AplicarConvenioAVentaUseCase aplicarConvenioAVentaUseCase(VentaRepositoryPort ventas,
            ConvenioSeguroRepositoryPort convenios, AfiliacionClienteRepositoryPort afiliaciones,
            CalcularCopagoUseCase calcularCopago, ClockPort clock) {
        return new AplicarConvenioAVentaUseCaseImpl(ventas, convenios, afiliaciones, calcularCopago, clock);
    }

    @Bean
    public RegistrarPagoUseCase registrarPagoUseCase(VentaRepositoryPort ventas, FormaPagoRepositoryPort formasPago,
            ProductoRepositoryPort productos, ClockPort clock) {
        return new RegistrarPagoUseCaseImpl(ventas, formasPago, productos, clock);
    }

    @Bean
    public ConfirmarVentaUseCase confirmarVentaUseCase(VentaRepositoryPort ventas,
            SesionCajaRepositoryPort sesionesCaja, ProductoRepositoryPort productos, LoteRepositoryPort lotes,
            ExistenciaLoteRepositoryPort existencias, MovimientoInventarioRepositoryPort movimientosInventario,
            ValidarRecetaUseCase validarReceta, ConvenioSeguroRepositoryPort convenios,
            AfiliacionClienteRepositoryPort afiliaciones, CalcularCopagoUseCase calcularCopago,
            ClienteRepositoryPort clientes, ValidarLineaCreditoUseCase validarLineaCredito,
            LineaCreditoRepositoryPort lineasCredito, MovimientoCreditoRepositoryPort movimientosCredito,
            FormaPagoRepositoryPort formasPago, ClockPort clock) {
        return new ConfirmarVentaUseCaseImpl(ventas, sesionesCaja, productos, lotes, existencias,
                movimientosInventario, validarReceta, convenios, afiliaciones, calcularCopago, clientes,
                validarLineaCredito, lineasCredito, movimientosCredito, formasPago, clock);
    }

    @Bean
    public AnularVentaUseCase anularVentaUseCase(VentaRepositoryPort ventas, LoteRepositoryPort lotes,
            ExistenciaLoteRepositoryPort existencias, MovimientoInventarioRepositoryPort movimientosInventario,
            ProductoRepositoryPort productos, ClockPort clock) {
        return new AnularVentaUseCaseImpl(ventas, lotes, existencias, movimientosInventario, productos, clock);
    }

    @Bean
    public ObtenerVentaUseCase obtenerVentaUseCase(VentaRepositoryPort ventas, ProductoRepositoryPort productos) {
        return new ObtenerVentaUseCaseImpl(ventas, productos);
    }

    @Bean
    public ConsultarVentasDiariasUseCase consultarVentasDiariasUseCase(VentaRepositoryPort ventas,
            ProductoRepositoryPort productos) {
        return new ConsultarVentasDiariasUseCaseImpl(ventas, productos);
    }

    @Bean
    public ConsultarFormasPagoUseCase consultarFormasPagoUseCase(FormaPagoRepositoryPort formasPago) {
        return new ConsultarFormasPagoUseCaseImpl(formasPago);
    }
}
