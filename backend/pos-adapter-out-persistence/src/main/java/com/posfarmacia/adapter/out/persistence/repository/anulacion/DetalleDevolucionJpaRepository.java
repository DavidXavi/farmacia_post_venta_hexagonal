package com.posfarmacia.adapter.out.persistence.repository.anulacion;

import com.posfarmacia.adapter.out.persistence.entity.anulacion.DetalleDevolucionJpaEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleDevolucionJpaRepository extends JpaRepository<DetalleDevolucionJpaEntity, UUID> {

    List<DetalleDevolucionJpaEntity> findByDevolucionId(UUID devolucionId);
}
