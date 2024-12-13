package com.everton.loterias.core.usecase.loterias.impl;

import com.everton.loterias.core.usecase.loterias.LoteriaUsecase;
import com.everton.loterias.core.usecase.loterias.strategy.LoteriaStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoteriaUsecaseImpl implements LoteriaUsecase {

    private final LoteriaStrategyFactory loteriaStrategyFactory;

    public void salvarMinhaLoteria(String tipoLoteria, List<Integer> numeros){
        var loteria = loteriaStrategyFactory.getStrategy(tipoLoteria);
        loteria.salvarMinhaLoteria(tipoLoteria, numeros);
        System.out.println("ssssss");
    }


}
