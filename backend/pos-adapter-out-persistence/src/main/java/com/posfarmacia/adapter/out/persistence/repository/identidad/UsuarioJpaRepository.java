package com.posfarmacia.adapter.out.persistence.repository.identidad;

import com.posfarmacia.adapter.out.persistence.entity.identidad.UsuarioJpaEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioJpaRepository extends JpaRepository<UsuarioJpaEntity, UUID> {

    Optional<UsuarioJpaEntity> findByNombreUsuario(String nombreUsuario);
}
