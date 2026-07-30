package com.posfarmacia.adapter.out.persistence.mapper.catalogo;

import com.posfarmacia.adapter.out.persistence.entity.catalogo.CategoriaJpaEntity;
import com.posfarmacia.domain.model.catalogo.Categoria;

public final class CategoriaMapper {

    private CategoriaMapper() {
    }

    public static Categoria aDominio(CategoriaJpaEntity entity) {
        return Categoria.reconstruir(entity.getId(), entity.getNombre());
    }

    public static CategoriaJpaEntity aEntidad(Categoria categoria) {
        return new CategoriaJpaEntity(categoria.getId(), categoria.getNombre());
    }
}
