package com.posfarmacia.adapter.out.persistence.repository.anulacion;

import com.posfarmacia.adapter.out.persistence.entity.anulacion.NotaCreditoJpaEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaCreditoJpaRepository extends JpaRepository<NotaCreditoJpaEntity, UUID> {
}
