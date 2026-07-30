package com.posfarmacia.bootstrap.configuration.identidad;

import com.posfarmacia.application.port.in.identidad.AbrirCajaUseCase;
import com.posfarmacia.application.port.in.identidad.AutenticarUsuarioUseCase;
import com.posfarmacia.application.port.in.identidad.CerrarCajaUseCase;
import com.posfarmacia.application.port.in.identidad.ConsultarAuditoriaUseCase;
import com.posfarmacia.application.port.in.identidad.ConsultarCajasUseCase;
import com.posfarmacia.application.port.in.identidad.ConsultarLocalesUseCase;
import com.posfarmacia.application.port.in.identidad.ConsultarSesionActivaUseCase;
import com.posfarmacia.application.port.in.identidad.GestionarRolUseCase;
import com.posfarmacia.application.port.in.identidad.GestionarUsuarioUseCase;
import com.posfarmacia.application.port.in.identidad.RegistrarAuditoriaUseCase;
import com.posfarmacia.application.port.out.ClockPort;
import com.posfarmacia.application.port.out.identidad.AuditoriaRepositoryPort;
import com.posfarmacia.application.port.out.identidad.CajaRepositoryPort;
import com.posfarmacia.application.port.out.identidad.LocalRepositoryPort;
import com.posfarmacia.application.port.out.identidad.PasswordHasherPort;
import com.posfarmacia.application.port.out.identidad.RolRepositoryPort;
import com.posfarmacia.application.port.out.identidad.SesionCajaRepositoryPort;
import com.posfarmacia.application.port.out.identidad.UsuarioRepositoryPort;
import com.posfarmacia.application.port.out.venta.FormaPagoRepositoryPort;
import com.posfarmacia.application.port.out.venta.VentaRepositoryPort;
import com.posfarmacia.application.usecase.identidad.AbrirCajaUseCaseImpl;
import com.posfarmacia.application.usecase.identidad.AutenticarUsuarioUseCaseImpl;
import com.posfarmacia.application.usecase.identidad.CerrarCajaUseCaseImpl;
import com.posfarmacia.application.usecase.identidad.ConsultarAuditoriaUseCaseImpl;
import com.posfarmacia.application.usecase.identidad.ConsultarCajasUseCaseImpl;
import com.posfarmacia.application.usecase.identidad.ConsultarLocalesUseCaseImpl;
import com.posfarmacia.application.usecase.identidad.ConsultarSesionActivaUseCaseImpl;
import com.posfarmacia.application.usecase.identidad.GestionarRolUseCaseImpl;
import com.posfarmacia.application.usecase.identidad.GestionarUsuarioUseCaseImpl;
import com.posfarmacia.application.usecase.identidad.RegistrarAuditoriaUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Cablea los casos de uso del contexto Identidad/Caja/Auditoria con sus adaptadores de
 * puertos de salida. Los casos de uso son POJOs de pos-application (sin anotaciones de
 * Spring, para que el nucleo sea probable sin framework); pos-bootstrap es el unico modulo
 * que conoce las implementaciones concretas y las conecta mediante @Bean.
 */
@Configuration
public class IdentidadUseCaseConfiguration {

    @Bean
    public AutenticarUsuarioUseCase autenticarUsuarioUseCase(
            UsuarioRepositoryPort usuarios, RolRepositoryPort roles, PasswordHasherPort passwordHasher) {
        return new AutenticarUsuarioUseCaseImpl(usuarios, roles, passwordHasher);
    }

    @Bean
    public AbrirCajaUseCase abrirCajaUseCase(CajaRepositoryPort cajas, SesionCajaRepositoryPort sesiones, ClockPort clock) {
        return new AbrirCajaUseCaseImpl(cajas, sesiones, clock);
    }

    @Bean
    public CerrarCajaUseCase cerrarCajaUseCase(SesionCajaRepositoryPort sesiones, VentaRepositoryPort ventas,
            FormaPagoRepositoryPort formasPago, ClockPort clock) {
        return new CerrarCajaUseCaseImpl(sesiones, ventas, formasPago, clock);
    }

    @Bean
    public RegistrarAuditoriaUseCase registrarAuditoriaUseCase(AuditoriaRepositoryPort auditoria, ClockPort clock) {
        return new RegistrarAuditoriaUseCaseImpl(auditoria, clock);
    }

    @Bean
    public ConsultarAuditoriaUseCase consultarAuditoriaUseCase(AuditoriaRepositoryPort auditoria) {
        return new ConsultarAuditoriaUseCaseImpl(auditoria);
    }

    @Bean
    public ConsultarCajasUseCase consultarCajasUseCase(CajaRepositoryPort cajas) {
        return new ConsultarCajasUseCaseImpl(cajas);
    }

    @Bean
    public ConsultarSesionActivaUseCase consultarSesionActivaUseCase(SesionCajaRepositoryPort sesiones) {
        return new ConsultarSesionActivaUseCaseImpl(sesiones);
    }

    @Bean
    public ConsultarLocalesUseCase consultarLocalesUseCase(LocalRepositoryPort locales) {
        return new ConsultarLocalesUseCaseImpl(locales);
    }

    @Bean
    public GestionarUsuarioUseCase gestionarUsuarioUseCase(
            UsuarioRepositoryPort usuarios, RolRepositoryPort roles, PasswordHasherPort passwordHasher) {
        return new GestionarUsuarioUseCaseImpl(usuarios, roles, passwordHasher);
    }

    @Bean
    public GestionarRolUseCase gestionarRolUseCase(RolRepositoryPort roles) {
        return new GestionarRolUseCaseImpl(roles);
    }
}
