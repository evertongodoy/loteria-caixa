package com.everton.loterias.core.gateway.database;

import com.everton.loterias.core.domain.*;

import java.util.UUID;

public interface LoteriaGateway {

    MinhaApostaDomain salvarMinhaLoteria(final ApostaDomain domain);
    CaixaDomain recuperarSorteio(final TipoLoteriaDomain tipoLoteriaDomain, final Integer numeroSorteio);
    CaixaDomain salvarSorteio(final ResultadoDomain resultadoDomain);
    MinhaApostaDomain recuperarApostasCaixa(final TipoLoteriaDomain tipoLoteriaDomain, final UUID uuid);

}
