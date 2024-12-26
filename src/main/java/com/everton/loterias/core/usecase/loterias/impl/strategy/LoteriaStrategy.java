package com.everton.loterias.core.usecase.loterias.impl.strategy;

import com.everton.loterias.core.domain.*;

import java.util.List;
import java.util.UUID;

public interface LoteriaStrategy {

    boolean seletorLoteria(final TipoLoteriaDomain tipoLoteriaDomain);
    MinhaApostaDomain salvarAposta(final ApostaDomain domain);
    CaixaDomain recuperarSorteioWeb(final Integer numero);
    CaixaDomain recuperarSorteioDataBase(final Integer numero);
    AtualizacaoDataBaseDomain atualizarDataBaseCaixaSorteio(final Integer timerMillis, final Integer qtdeRegistros);
    MinhaApostaDomain recuperarMinnasApostasCaixa(final UUID uuid);
    CheckApostasDomain checarApostaVencedora(final UUID uuid, final List<Integer> apostaSimulada);

}
