package com.everton.loterias.core.usecase.loterias.impl.strategy;

import com.everton.loterias.core.domain.*;

public interface LoteriaStrategy {

    boolean seletorLoteria(final TipoLoteriaDomain tipoLoteriaDomain);
    MinhaApostaDomain salvarAposta(final ApostaDomain domain);
    CaixaDomain recuperarSorteioWeb(final Integer numero);
    CaixaDomain recuperarSorteioDataBase(final Integer numero);
    AtualizacaoDataBaseDomain atualizarDataBaseCaixaSorteio(final Integer timerMillis, final Integer qtdeRegistros);

}
