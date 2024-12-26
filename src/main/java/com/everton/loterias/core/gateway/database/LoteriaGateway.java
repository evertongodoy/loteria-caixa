package com.everton.loterias.core.gateway.database;

import com.everton.loterias.core.domain.*;

public interface LoteriaGateway {

    MinhaApostaDomain salvarMinhaLoteria(final ApostaDomain domain);
    CaixaDomain recuperarSorteio(final TipoLoteriaDomain tipoLoteriaDomain, final Integer numeroSorteio);
    CaixaDomain salvarSorteio(final ResultadoDomain resultadoDomain);

}
