package com.posfarmacia.adapter.out.persistence.repository.catalogo;

import com.posfarmacia.adapter.out.persistence.entity.catalogo.CategoriaJpaEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaJpaRepository extends JpaRepository<CategoriaJpaEntity, UUID> {
}
