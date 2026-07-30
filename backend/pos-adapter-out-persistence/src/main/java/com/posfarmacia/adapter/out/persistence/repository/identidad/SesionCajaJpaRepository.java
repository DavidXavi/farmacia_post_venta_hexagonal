package com.posfarmacia.adapter.out.persistence.repository.identidad;

import com.posfarmacia.adapter.out.persistence.entity.identidad.SesionCajaJpaEntity;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SesionCajaJpaRepository extends JpaRepository<SesionCajaJpaEntity, UUID> {

    Optional<SesionCajaJpaEntity> findFirstByCajaIdAndEstado(UUID cajaId, String estado);
}
