package com.posfarmacia.adapter.out.persistence.repository.inventario;

import com.posfarmacia.adapter.out.persistence.entity.inventario.LoteJpaEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoteJpaRepository extends JpaRepository<LoteJpaEntity, UUID> {

    List<LoteJpaEntity> findByProductoId(UUID productoId);

    List<LoteJpaEntity> findByProductoIdAndLocalId(UUID productoId, UUID localId);
}
