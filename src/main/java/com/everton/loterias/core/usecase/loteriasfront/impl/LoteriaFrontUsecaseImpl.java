package com.everton.loterias.core.usecase.loteriasfront.impl;

import com.everton.loterias.core.domain.TipoLoteriaDomain;
import com.everton.loterias.core.usecase.loteriasfront.LoteriaFrontUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class LoteriaFrontUsecaseImpl implements LoteriaFrontUsecase {

    @Override
    public List<Integer> numerosDisponiveis(String tipoLoteria) {
        return this.montarListaDisponiveis(tipoLoteria);
    }

    @Override
    public List<Integer> convertNumerosSelecionados(String numeros) {
        return Arrays.stream(numeros.replaceAll("\\s", "").split(","))
                .map(Integer::parseInt) // Converter cada elemento para Integer
                .distinct() // garantir que a lista final não contenha duplicatas
                .toList();
    }

    @Override
    public Integer quantidadeMinimaNumerosJogo(String tipoLoteria) {
        var tipoJogo = TipoLoteriaDomain.fromDescricao(tipoLoteria.toUpperCase());
        switch(tipoJogo){
            case LOTOMANIA -> {
                return 50;
            }
            case LOTOFACIL -> {
                return 15;
            }
            default -> {
                return 0;
            }
        }
    }

    private List<Integer> montarListaDisponiveis(String tipoLoteria) {
        var tipoJogo = TipoLoteriaDomain.fromDescricao(tipoLoteria.toUpperCase());

        switch(tipoJogo){
            case LOTOMANIA -> {
                return IntStream.rangeClosed(0, 99)
                        .boxed()
                        .collect(Collectors.toList());
            }
            case LOTOFACIL -> {
                return IntStream.rangeClosed(1, 25)
                        .boxed()
                        .collect(Collectors.toList());
            }
            default -> {
                return List.of();
            }
        }
    }
}
