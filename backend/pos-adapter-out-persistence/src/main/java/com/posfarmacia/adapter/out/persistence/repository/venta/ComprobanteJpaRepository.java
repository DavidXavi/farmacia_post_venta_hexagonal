package com.posfarmacia.adapter.out.persistence.repository.venta;

import com.posfarmacia.adapter.out.persistence.entity.venta.ComprobanteJpaEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComprobanteJpaRepository extends JpaRepository<ComprobanteJpaEntity, UUID> {

    Optional<ComprobanteJpaEntity> findByVentaId(UUID ventaId);

    void deleteByVentaId(UUID ventaId);
}
