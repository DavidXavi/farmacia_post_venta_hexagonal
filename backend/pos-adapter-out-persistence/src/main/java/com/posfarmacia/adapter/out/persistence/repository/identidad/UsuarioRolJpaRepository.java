package com.posfarmacia.adapter.out.persistence.repository.identidad;

import com.posfarmacia.adapter.out.persistence.entity.identidad.UsuarioRolId;
import com.posfarmacia.adapter.out.persistence.entity.identidad.UsuarioRolJpaEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRolJpaRepository extends JpaRepository<UsuarioRolJpaEntity, UsuarioRolId> {

    List<UsuarioRolJpaEntity> findByUsuarioId(UUID usuarioId);

    void deleteByUsuarioId(UUID usuarioId);
}
