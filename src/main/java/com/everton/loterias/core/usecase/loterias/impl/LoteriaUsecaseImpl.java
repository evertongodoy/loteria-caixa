package com.everton.loterias.core.usecase.loterias.impl;

import com.everton.loterias.core.domain.LoteriaDomain;
import com.everton.loterias.core.usecase.loterias.LoteriaUsecase;
import com.everton.loterias.core.usecase.loterias.impl.strategy.LoteriaStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LoteriaUsecaseImpl implements LoteriaUsecase {

    private final LoteriaStrategyFactory loteriaStrategyFactory;

    public LoteriaDomain salvarMinhaAposta(final String tipoLoteria,
                                           final List<Integer> numeros,
                                           final boolean isAtivo){
        var domain = this.construirLoteriaDomain(tipoLoteria, numeros, isAtivo);
        return loteriaStrategyFactory.getStrategy(tipoLoteria).salvarAposta(domain);
    }

    @Override
    public void atualizarBaseLoteria(String tipoLoteria) {
        loteriaStrategyFactory.getStrategy(tipoLoteria).atualizarBaseLoteria(tipoLoteria);
    }


    private LoteriaDomain construirLoteriaDomain(final String tipoLoteria,
                                                 final List<Integer> numeros,
                                                 final boolean isAtivo){
        return LoteriaDomain.builder()
                .uuid(UUID.randomUUID())
                .tipoJogo(tipoLoteria)
                .numeros(numeros)
                .inicio(LocalDate.now())
                .ativo(isAtivo)
                .build();
    }

}