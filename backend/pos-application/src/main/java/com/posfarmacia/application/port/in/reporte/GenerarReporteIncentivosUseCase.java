package com.posfarmacia.application.port.in.reporte;

import com.posfarmacia.application.dto.reporte.IncentivoResumenResult;
import com.posfarmacia.application.dto.reporte.ReporteIncentivosQuery;
import java.util.List;

/** Puerto de entrada RF18: reporte de incentivos por trabajador, producto y regla aplicada. */
public interface GenerarReporteIncentivosUseCase {

    List<IncentivoResumenResult> generar(ReporteIncentivosQuery query);
}
