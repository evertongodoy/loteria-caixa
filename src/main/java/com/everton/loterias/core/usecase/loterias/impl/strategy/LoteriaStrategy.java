package com.everton.loterias.core.usecase.loterias.impl.strategy;

import com.everton.loterias.core.domain.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public interface LoteriaStrategy {

    boolean seletorLoteria(final TipoLoteriaDomain tipoLoteriaDomain);
    MinhaApostaDomain salvarAposta(final ApostaDomain domain);
    CaixaDomain recuperarSorteioWeb(final Integer numero);
    CaixaDomain recuperarSorteioDataBase(final Integer numero);
    AtualizacaoDataBaseDomain atualizarDataBaseCaixaSorteio(final Integer timerMillis, final Integer qtdeRegistros);
    MinhaApostaDomain recuperarMinnasApostasCaixa(final UUID uuid);
    CheckApostasDomain checarApostaVencedora(final UUID uuid, final List<Integer> apostaSimulada);


    default Map<Integer, Long> montarMaioresAcertos(final List<ApostaCheckedDomain> apostasChecadas){
        return apostasChecadas.stream()
                .collect(Collectors.groupingBy(
                        ApostaCheckedDomain::getTotalAcertos,
                        Collectors.counting()
                ));
    }
}
