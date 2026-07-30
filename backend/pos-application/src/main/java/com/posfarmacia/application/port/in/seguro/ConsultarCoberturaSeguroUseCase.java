package com.posfarmacia.application.port.in.seguro;

import com.posfarmacia.application.dto.seguro.ConsultarCoberturaCommand;
import com.posfarmacia.application.dto.seguro.ConsultarCoberturaResult;

/**
 * RF10, RN22-RN27: identifica al cliente por DNI, consulta la afiliacion/cobertura en la
 * central y calcula el copago. Endpoint POST /api/v1/seguros/coberturas/consultar.
 */
public interface ConsultarCoberturaSeguroUseCase {

    ConsultarCoberturaResult consultar(ConsultarCoberturaCommand command);
}
