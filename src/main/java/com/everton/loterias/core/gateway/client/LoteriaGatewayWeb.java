package com.everton.loterias.core.gateway.client;

import com.everton.loterias.core.domain.CaixaDomain;
import com.everton.loterias.core.domain.TipoLoteriaDomain;

public interface LoteriaGatewayWeb {

    String URL = "https://servicebus2.caixa.gov.br/portaldeloterias/api";

    CaixaDomain recuperarSorteio(final TipoLoteriaDomain tipoLoteriaDomain, final Integer numeroSorteio);

}