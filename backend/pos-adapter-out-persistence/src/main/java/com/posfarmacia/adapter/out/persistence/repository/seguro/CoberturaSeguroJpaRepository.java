package com.posfarmacia.adapter.out.persistence.repository.seguro;

import com.posfarmacia.adapter.out.persistence.entity.seguro.CoberturaSeguroJpaEntity;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoberturaSeguroJpaRepository extends JpaRepository<CoberturaSeguroJpaEntity, UUID> {

    List<CoberturaSeguroJpaEntity> findByConvenioId(UUID convenioId);

    Optional<CoberturaSeguroJpaEntity> findByConvenioIdAndProductoId(UUID convenioId, UUID productoId);
}
