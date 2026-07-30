package com.posfarmacia.adapter.out.persistence.repository.identidad;

import com.posfarmacia.adapter.out.persistence.entity.identidad.LocalJpaEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalJpaRepository extends JpaRepository<LocalJpaEntity, UUID> {
}
