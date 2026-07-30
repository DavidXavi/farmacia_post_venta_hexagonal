package com.posfarmacia.adapter.out.persistence.repository.identidad;

import com.posfarmacia.adapter.out.persistence.entity.identidad.RolJpaEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolJpaRepository extends JpaRepository<RolJpaEntity, UUID> {

    Optional<RolJpaEntity> findByNombre(String nombre);
}
