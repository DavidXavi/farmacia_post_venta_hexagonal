package com.posfarmacia.application.port.in.reporte;

import com.posfarmacia.application.dto.reporte.LoteProximoAVencerResult;
import java.util.List;

/** Puerto de entrada RF17/RN36: lotes vendibles que vencen dentro del horizonte indicado (dias). */
public interface ConsultarLotesProximosAVencerUseCase {

    List<LoteProximoAVencerResult> consultar(int diasHorizonte);
}
