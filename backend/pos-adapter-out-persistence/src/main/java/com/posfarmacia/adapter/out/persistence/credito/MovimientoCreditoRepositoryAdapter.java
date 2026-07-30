package com.posfarmacia.adapter.out.persistence.credito;

import com.posfarmacia.adapter.out.persistence.mapper.credito.MovimientoCreditoMapper;
import com.posfarmacia.adapter.out.persistence.repository.credito.MovimientoCreditoJpaRepository;
import com.posfarmacia.application.port.out.credito.MovimientoCreditoRepositoryPort;
import com.posfarmacia.domain.model.credito.MovimientoCredito;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class MovimientoCreditoRepositoryAdapter implements MovimientoCreditoRepositoryPort {

    private final MovimientoCreditoJpaRepository movimientoCreditoJpaRepository;
    private final MovimientoCreditoMapper movimientoCreditoMapper;

    public MovimientoCreditoRepositoryAdapter(MovimientoCreditoJpaRepository movimientoCreditoJpaRepository,
                                               MovimientoCreditoMapper movimientoCreditoMapper) {
        this.movimientoCreditoJpaRepository = movimientoCreditoJpaRepository;
        this.movimientoCreditoMapper = movimientoCreditoMapper;
    }

    @Override
    public MovimientoCredito guardar(MovimientoCredito movimiento) {
        var guardado = movimientoCreditoJpaRepository.save(movimientoCreditoMapper.toEntity(movimiento));
        return movimientoCreditoMapper.toDomain(guardado);
    }

    @Override
    public List<MovimientoCredito> buscarPorLineaCredito(UUID lineaCreditoId) {
        return movimientoCreditoJpaRepository.findByLineaCreditoId(lineaCreditoId).stream()
                .map(movimientoCreditoMapper::toDomain)
                .toList();
    }
}
