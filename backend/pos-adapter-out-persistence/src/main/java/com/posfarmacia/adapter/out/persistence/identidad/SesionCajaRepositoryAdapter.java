package com.posfarmacia.adapter.out.persistence.identidad;

import com.posfarmacia.adapter.out.persistence.mapper.identidad.SesionCajaMapper;
import com.posfarmacia.adapter.out.persistence.repository.identidad.SesionCajaJpaRepository;
import com.posfarmacia.application.port.out.identidad.SesionCajaRepositoryPort;
import com.posfarmacia.domain.enums.EstadoCaja;
import com.posfarmacia.domain.model.identidad.SesionCaja;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class SesionCajaRepositoryAdapter implements SesionCajaRepositoryPort {

    private final SesionCajaJpaRepository sesiones;

    public SesionCajaRepositoryAdapter(SesionCajaJpaRepository sesiones) {
        this.sesiones = sesiones;
    }

    @Override
    public Optional<SesionCaja> buscarSesionActiva(UUID cajaId) {
        return sesiones.findFirstByCajaIdAndEstado(cajaId, EstadoCaja.ABIERTA.name()).map(SesionCajaMapper::aDominio);
    }

    @Override
    public SesionCaja guardar(SesionCaja sesionCaja) {
        sesiones.save(SesionCajaMapper.aEntidad(sesionCaja));
        return sesionCaja;
    }
}
