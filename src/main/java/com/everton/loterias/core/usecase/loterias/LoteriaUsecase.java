package com.everton.loterias.core.usecase.loterias;

import com.everton.loterias.core.domain.LoteriaDomain;

import java.util.List;

public interface LoteriaUsecase {

    LoteriaDomain salvarMinhaAposta(final String tipoLoteria, final List<Integer> numeros, final boolean isAtivo);
    void atualizarBaseLoteria(final String tipoLoteria);
}
