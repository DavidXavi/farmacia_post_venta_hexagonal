package com.posfarmacia.adapter.out.persistence.repository.identidad;

import com.posfarmacia.adapter.out.persistence.entity.identidad.CajaJpaEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CajaJpaRepository extends JpaRepository<CajaJpaEntity, UUID> {
}
