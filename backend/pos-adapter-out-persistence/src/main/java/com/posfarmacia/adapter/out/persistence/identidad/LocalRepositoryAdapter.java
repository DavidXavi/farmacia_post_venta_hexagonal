package com.posfarmacia.adapter.out.persistence.identidad;

import com.posfarmacia.adapter.out.persistence.mapper.identidad.LocalMapper;
import com.posfarmacia.adapter.out.persistence.repository.identidad.LocalJpaRepository;
import com.posfarmacia.application.port.out.identidad.LocalRepositoryPort;
import com.posfarmacia.domain.model.identidad.Local;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class LocalRepositoryAdapter implements LocalRepositoryPort {

    private final LocalJpaRepository locales;

    public LocalRepositoryAdapter(LocalJpaRepository locales) {
        this.locales = locales;
    }

    @Override
    public List<Local> listarTodos() {
        return locales.findAll().stream().map(LocalMapper::aDominio).toList();
    }
}
