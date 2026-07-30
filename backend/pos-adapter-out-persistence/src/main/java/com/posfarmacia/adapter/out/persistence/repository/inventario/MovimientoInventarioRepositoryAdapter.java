package com.posfarmacia.adapter.out.persistence.repository.inventario;

import com.posfarmacia.adapter.out.persistence.mapper.inventario.MovimientoInventarioMapper;
import com.posfarmacia.application.port.out.inventario.MovimientoInventarioRepositoryPort;
import com.posfarmacia.domain.model.inventario.MovimientoInventario;
import org.springframework.stereotype.Component;

@Component
public class MovimientoInventarioRepositoryAdapter implements MovimientoInventarioRepositoryPort {

    private final MovimientoInventarioJpaRepository jpaRepository;

    public MovimientoInventarioRepositoryAdapter(MovimientoInventarioJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public MovimientoInventario guardar(MovimientoInventario movimiento) {
        var guardado = jpaRepository.save(MovimientoInventarioMapper.aEntidad(movimiento));
        return MovimientoInventarioMapper.aDominio(guardado);
    }
}
