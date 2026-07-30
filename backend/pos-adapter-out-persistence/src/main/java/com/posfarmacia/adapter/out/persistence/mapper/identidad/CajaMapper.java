package com.posfarmacia.adapter.out.persistence.mapper.identidad;

import com.posfarmacia.adapter.out.persistence.entity.identidad.CajaJpaEntity;
import com.posfarmacia.domain.model.identidad.Caja;

public final class CajaMapper {

    private CajaMapper() {
    }

    public static Caja aDominio(CajaJpaEntity entity) {
        return new Caja(entity.getId(), entity.getNombre(), entity.getLocalId(), entity.isActiva());
    }

    public static CajaJpaEntity aEntidad(Caja caja) {
        return new CajaJpaEntity(caja.getId(), caja.getNombre(), caja.getLocalId(), caja.isActiva());
    }
}
