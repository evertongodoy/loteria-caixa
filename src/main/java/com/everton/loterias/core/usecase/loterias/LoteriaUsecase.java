package com.everton.loterias.core.usecase.loterias;

import com.everton.loterias.core.domain.*;

import java.util.List;

public interface LoteriaUsecase {

    ApostaDomain salvarMinhaAposta(final String tipoLoteria, final List<Integer> numeros, final boolean isAtivo);

    CaixaDomain recuperarSorteioWeb(final String tipoLoteria, final Integer numero);

    CaixaDomain recuperarSorteioDataBase(final String tipoLoteria, final Integer numero);

    AtualizacaoDataBaseDomain atualizarBaseLoteria(final String tipoLoteria, final Integer timer, final Integer qtdeRegistros);

}
