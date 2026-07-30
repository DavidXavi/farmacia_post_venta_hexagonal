package com.posfarmacia.adapter.out.persistence.repository.inventario;

import com.posfarmacia.adapter.out.persistence.entity.inventario.ExistenciaLoteJpaEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExistenciaLoteJpaRepository extends JpaRepository<ExistenciaLoteJpaEntity, UUID> {

    Optional<ExistenciaLoteJpaEntity> findByProductoIdAndLocalId(UUID productoId, UUID localId);

    List<ExistenciaLoteJpaEntity> findByLocalId(UUID localId);
}
