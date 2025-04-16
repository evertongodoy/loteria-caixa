package com.everton.loterias.core.usecase.loterias.impl;

import com.everton.loterias.core.domain.*;
import com.everton.loterias.core.gateway.database.LoteriaGateway;
import com.everton.loterias.core.usecase.loterias.LoteriaUsecase;
import com.everton.loterias.core.usecase.loterias.impl.strategy.LoteriaStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoteriaUsecaseImpl implements LoteriaUsecase {

    private final LoteriaGateway loteriaGateway;
    private final LoteriaStrategyFactory loteriaStrategyFactory;

    public MinhaApostaDomain salvarMinhaAposta(final String tipoLoteria,
                                               final List<Integer> numeros,
                                               final boolean ativo) {
        return loteriaStrategyFactory.getStrategy(TipoLoteriaDomain.fromDescricao(tipoLoteria.toUpperCase()))
                .salvarAposta(this.construirLoteriaDomain(tipoLoteria, numeros, ativo));
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

    @Override
    public MaisSorteadosDomain recuperarMaisSorteados(String tipoLoteria, Integer numero) {
        var tipoJogo = TipoLoteriaDomain.fromDescricao(tipoLoteria.toUpperCase());
        var caixaDomain = loteriaStrategyFactory.getStrategy(tipoJogo).recuperarSorteioDataBase(numero);
        var maisSorteados = this.contarNumerosSorteados(caixaDomain.getResultados());
        var porcentagemSaida = this.calcularPorcentagemSaida(caixaDomain.getResultados(), maisSorteados);
        return MaisSorteadosDomain.builder()
                .totalLoterias(caixaDomain.getResultados().size())
                .numerosMaisSorteados(maisSorteados)
                .porcentagemSaida(porcentagemSaida)
                .build();
    }

    private ApostaDomain construirLoteriaDomain(final String tipoLoteria,
                                                final List<Integer> numeros,
                                                final boolean isAtivo) {
        return ApostaDomain.builder()
                .uuid(UUID.randomUUID())
                .tipoJogo(tipoLoteria)
                .numeros(numeros)
                .inicio(LocalDate.now())
                .ativo(isAtivo)
                .build();
    }

    private Map<Integer, Integer> contarNumerosSorteados(List<ResultadoDomain> resultados) {
        Map<Integer, Integer> contagemNumeros = new HashMap<>();

        for (ResultadoDomain resultado : resultados) {
            for (Integer numero : resultado.getListaDezenas()) {
                contagemNumeros.put(numero, contagemNumeros.getOrDefault(numero, 0) + 1);
            }
        }
        return this.ordenarResultados(contagemNumeros);
    }

    private Map<Integer, Integer> ordenarResultados(final Map<Integer, Integer> maisSorteados) {
        return maisSorteados.entrySet()
                .stream()
                .sorted((e1, e2) -> {
                    int valueComparison = e2.getValue().compareTo(e1.getValue()); // Ordenar por valor (decrescente)
                    return valueComparison != 0 ? valueComparison : e1.getKey().compareTo(e2.getKey()); // Empate: ordenar por chave (crescente)
                })
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new // Preservar a ordem de classificação
                ));
    }

    private Map<Integer, Double> calcularPorcentagemSaida(final List<ResultadoDomain> resultados,
                                                          final Map<Integer, Integer> maisSorteados){
        return maisSorteados.entrySet()
                .stream()
                .sorted((e1, e2) -> Double.compare(
                        (e2.getValue() / (double) resultados.size()) * 100,
                        (e1.getValue() / (double) resultados.size()) * 100
                )) // Ordenar por porcentagem (decrescente)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> (entry.getValue() / (double) resultados.size()) * 100, // Porcentagem
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new // Preservar a ordem de classificação
                ));
    }

}