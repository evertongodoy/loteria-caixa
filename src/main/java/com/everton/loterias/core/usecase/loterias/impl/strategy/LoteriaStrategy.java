package com.everton.loterias.core.usecase.loterias.impl.strategy;

import com.everton.loterias.core.domain.LoteriaDomain;

public interface LoteriaStrategy {

    boolean seletorLoteria(final String tipoLoteria);
    LoteriaDomain salvarAposta(final LoteriaDomain domain);
    void atualizarBaseLoteria(final String tipoLoteria);

}
