package com.posfarmacia.adapter.out.persistence.repository.catalogo;

import com.posfarmacia.adapter.out.persistence.mapper.catalogo.PresentacionMapper;
import com.posfarmacia.application.port.out.inventario.PresentacionRepositoryPort;
import com.posfarmacia.domain.model.catalogo.Presentacion;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class PresentacionRepositoryAdapter implements PresentacionRepositoryPort {

    private final PresentacionJpaRepository jpaRepository;

    public PresentacionRepositoryAdapter(PresentacionJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Presentacion guardar(Presentacion presentacion) {
        var guardado = jpaRepository.save(PresentacionMapper.aEntidad(presentacion));
        return PresentacionMapper.aDominio(guardado);
    }

    @Override
    public List<Presentacion> listarTodos() {
        return jpaRepository.findAll().stream().map(PresentacionMapper::aDominio).toList();
    }

    @Override
    public Optional<Presentacion> buscarPorId(UUID id) {
        return jpaRepository.findById(id).map(PresentacionMapper::aDominio);
    }
}
