package com.posfarmacia.adapter.out.persistence.repository.inventario;

import com.posfarmacia.adapter.out.persistence.entity.inventario.MovimientoInventarioJpaEntity;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoInventarioJpaRepository extends JpaRepository<MovimientoInventarioJpaEntity, UUID> {
}
