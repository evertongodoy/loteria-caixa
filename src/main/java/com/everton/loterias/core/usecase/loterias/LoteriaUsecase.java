package com.everton.loterias.core.usecase.loterias;

import com.everton.loterias.core.domain.*;

import java.util.List;
import java.util.UUID;

public interface LoteriaUsecase {

    MinhaApostaDomain salvarMinhaAposta(final String tipoLoteria, final List<Integer> numeros, final boolean isAtivo);

    CaixaDomain recuperarSorteioWeb(final String tipoLoteria, final Integer numero);

    CaixaDomain recuperarSorteioDataBase(final String tipoLoteria, final Integer numero);

    AtualizacaoDataBaseDomain atualizarBaseLoteria(final String tipoLoteria, final Integer timer, final Integer qtdeRegistros);

    MinhaApostaDomain recuperarMinhasApostas(final String tipoLoteria, final UUID uuid);

    CheckApostasDomain validarAposta(final String tipoLoteria, final UUID uuid, final List<Integer> apostaSimulada);

}
