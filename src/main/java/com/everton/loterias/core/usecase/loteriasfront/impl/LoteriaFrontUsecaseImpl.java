package com.everton.loterias.core.usecase.loteriasfront.impl;

import com.everton.loterias.core.domain.TipoLoteriaDomain;
import com.everton.loterias.core.usecase.loteriasfront.LoteriaFrontUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
