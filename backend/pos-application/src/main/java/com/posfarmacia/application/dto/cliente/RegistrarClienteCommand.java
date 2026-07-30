package com.posfarmacia.application.dto.cliente;

import java.time.LocalDate;

public record RegistrarClienteCommand(
        String dni,
        String nombres,
        String apellidos,
        LocalDate fechaNacimiento,
        String telefono,
        String correo,
        String direccion) {
}
