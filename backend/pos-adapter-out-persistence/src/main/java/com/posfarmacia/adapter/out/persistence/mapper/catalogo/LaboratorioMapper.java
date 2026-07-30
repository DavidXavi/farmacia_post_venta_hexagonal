package com.posfarmacia.adapter.out.persistence.mapper.catalogo;

import com.posfarmacia.adapter.out.persistence.entity.catalogo.LaboratorioJpaEntity;
import com.posfarmacia.domain.model.catalogo.Laboratorio;

public final class LaboratorioMapper {

    private LaboratorioMapper() {
    }

    public static Laboratorio aDominio(LaboratorioJpaEntity entity) {
        return Laboratorio.reconstruir(entity.getId(), entity.getNombre());
    }

    public static LaboratorioJpaEntity aEntidad(Laboratorio laboratorio) {
        return new LaboratorioJpaEntity(laboratorio.getId(), laboratorio.getNombre());
    }
}
