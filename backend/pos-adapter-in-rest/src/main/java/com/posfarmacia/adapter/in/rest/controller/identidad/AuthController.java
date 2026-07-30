package com.posfarmacia.adapter.in.rest.controller.identidad;

import com.posfarmacia.adapter.in.rest.request.identidad.LoginRequest;
import com.posfarmacia.adapter.in.rest.response.identidad.LoginResponse;
import com.posfarmacia.adapter.in.rest.security.JwtTokenIssuer;
import com.posfarmacia.application.dto.identidad.UsuarioAutenticado;
import com.posfarmacia.application.port.in.identidad.AutenticarUsuarioUseCase;
import com.posfarmacia.domain.enums.PermisoEspecial;
import com.posfarmacia.domain.enums.RolNombre;
import jakarta.validation.Valid;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** RF01: autenticacion y control de acceso. */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AutenticarUsuarioUseCase autenticarUsuario;
    private final JwtTokenIssuer jwtTokenIssuer;

    public AuthController(AutenticarUsuarioUseCase autenticarUsuario, JwtTokenIssuer jwtTokenIssuer) {
        this.autenticarUsuario = autenticarUsuario;
        this.jwtTokenIssuer = jwtTokenIssuer;
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        UsuarioAutenticado usuario = autenticarUsuario.autenticar(request.nombreUsuario(), request.password());
        String token = jwtTokenIssuer.emitir(usuario);

        Set<String> roles = usuario.roles().stream().map(RolNombre::name).collect(Collectors.toSet());
        Set<String> permisos = usuario.permisos().stream().map(PermisoEspecial::name).collect(Collectors.toSet());

        return new LoginResponse(token, usuario.usuarioId(), usuario.nombreUsuario(), roles, permisos, usuario.localId());
    }
}
