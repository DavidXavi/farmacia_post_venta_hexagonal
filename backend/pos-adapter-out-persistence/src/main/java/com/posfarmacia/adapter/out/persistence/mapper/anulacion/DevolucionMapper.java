package com.posfarmacia.adapter.out.persistence.mapper.anulacion;

import com.posfarmacia.adapter.out.persistence.entity.anulacion.DevolucionJpaEntity;
import com.posfarmacia.domain.model.anulacion.DetalleDevolucion;
import com.posfarmacia.domain.model.anulacion.Devolucion;
import java.util.List;

public final class DevolucionMapper {

    private DevolucionMapper() {
    }

    public static Devolucion aDominio(DevolucionJpaEntity entity, List<DetalleDevolucion> detalles) {
        return Devolucion.reconstruir(entity.getId(), entity.getVentaId(), entity.getUsuarioId(), entity.getMotivo(),
                entity.getFecha(), detalles);
    }

    public static DevolucionJpaEntity aEntidad(Devolucion devolucion) {
        return new DevolucionJpaEntity(devolucion.getId(), devolucion.getVentaId(), devolucion.getUsuarioId(),
                devolucion.getMotivo(), devolucion.getFecha());
    }
}
