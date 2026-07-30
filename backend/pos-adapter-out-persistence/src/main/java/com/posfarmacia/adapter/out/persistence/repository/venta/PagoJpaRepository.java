package com.posfarmacia.adapter.out.persistence.repository.venta;

import com.posfarmacia.adapter.out.persistence.entity.venta.PagoJpaEntity;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoJpaRepository extends JpaRepository<PagoJpaEntity, UUID> {

    List<PagoJpaEntity> findByVentaId(UUID ventaId);

    void deleteByVentaId(UUID ventaId);
}
