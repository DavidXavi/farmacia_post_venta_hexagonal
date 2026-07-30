package com.posfarmacia.application.port.in.identidad;

import com.posfarmacia.application.dto.identidad.UsuarioAutenticado;

/**
 * RF01: valida usuario/contrasena. Lanza {@code CredencialesInvalidasException} si el
 * usuario no existe, la contrasena no coincide o la cuenta no esta activa. No genera JWT.
 */
public interface AutenticarUsuarioUseCase {

    UsuarioAutenticado autenticar(String nombreUsuario, String password);
}
