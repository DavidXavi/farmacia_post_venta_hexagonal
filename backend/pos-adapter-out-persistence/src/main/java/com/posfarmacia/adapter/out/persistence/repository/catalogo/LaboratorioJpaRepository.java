package com.posfarmacia.adapter.out.persistence.repository.catalogo;

import com.posfarmacia.adapter.out.persistence.entity.catalogo.LaboratorioJpaEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaboratorioJpaRepository extends JpaRepository<LaboratorioJpaEntity, UUID> {
}
