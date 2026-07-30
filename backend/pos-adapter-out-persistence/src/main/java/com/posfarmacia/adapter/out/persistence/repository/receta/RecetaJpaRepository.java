package com.posfarmacia.adapter.out.persistence.repository.receta;

import com.posfarmacia.adapter.out.persistence.entity.receta.RecetaJpaEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecetaJpaRepository extends JpaRepository<RecetaJpaEntity, UUID> {

    Optional<RecetaJpaEntity> findByNumero(String numero);
}
