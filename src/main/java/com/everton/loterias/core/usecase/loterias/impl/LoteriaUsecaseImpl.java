package com.everton.loterias.core.usecase.loterias.impl;

import com.everton.loterias.core.domain.*;
import com.everton.loterias.core.gateway.database.LoteriaGateway;
import com.everton.loterias.core.usecase.loterias.LoteriaUsecase;
import com.everton.loterias.core.usecase.loterias.impl.strategy.LoteriaStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoteriaUsecaseImpl implements LoteriaUsecase {

    private final LoteriaGateway loteriaGateway;
    private final LoteriaStrategyFactory loteriaStrategyFactory;

    public MinhaApostaDomain salvarMinhaAposta(final String tipoLoteria,
                                               final List<Integer> numeros,
                                               final boolean isAtivo){
        return loteriaStrategyFactory.getStrategy(TipoLoteriaDomain.fromDescricao(tipoLoteria.toUpperCase()))
                .salvarAposta(this.construirLoteriaDomain(tipoLoteria, numeros, isAtivo));
    }

    @Override
    public CaixaDomain recuperarSorteioWeb(final String tipoLoteria, final Integer numero) {
        var tipoJogo = TipoLoteriaDomain.fromDescricao(tipoLoteria.toUpperCase());
        return loteriaStrategyFactory.getStrategy(tipoJogo).recuperarSorteioWeb(numero);
    }

    @Override
    public CaixaDomain recuperarSorteioDataBase(final String tipoLoteria, final Integer numero) {
        var tipoJogo = TipoLoteriaDomain.fromDescricao(tipoLoteria.toUpperCase());
        return loteriaStrategyFactory.getStrategy(tipoJogo).recuperarSorteioDataBase(numero);
    }

    @Override
    public AtualizacaoDataBaseDomain atualizarBaseLoteria(final String tipoLoteria, final Integer timerMillis, final Integer qtdeRegistros) {
        var tipoJogo = TipoLoteriaDomain.fromDescricao(tipoLoteria.toUpperCase());
        return loteriaStrategyFactory.getStrategy(tipoJogo).atualizarDataBaseCaixaSorteio(timerMillis, qtdeRegistros);
    }

    @Override
    public MinhaApostaDomain recuperarMinhasApostas(final String tipoLoteria, final UUID uuid) {
        var tipoJogo = TipoLoteriaDomain.fromDescricao(tipoLoteria.toUpperCase());
        return loteriaStrategyFactory.getStrategy(tipoJogo).recuperarMinnasApostasCaixa(uuid);
    }

    @Override
    public CheckApostasDomain validarAposta(final String tipoLoteria, final UUID uuid, final List<Integer> apostaSimulada) {
        var tipoJogo = TipoLoteriaDomain.fromDescricao(tipoLoteria.toUpperCase());
        return loteriaStrategyFactory.getStrategy(tipoJogo).checarApostaVencedora(uuid, apostaSimulada);
    }

    @Override
    public void deletarAposta(final UUID uuid) {
        loteriaGateway.deletarAposta(uuid);
    }

    private ApostaDomain construirLoteriaDomain(final String tipoLoteria,
                                                      final List<Integer> numeros,
                                                      final boolean isAtivo){
        return ApostaDomain.builder()
                .uuid(UUID.randomUUID())
                .tipoJogo(tipoLoteria)
                .numeros(numeros)
                .inicio(LocalDate.now())
                .ativo(isAtivo)
                .build();
    }

}