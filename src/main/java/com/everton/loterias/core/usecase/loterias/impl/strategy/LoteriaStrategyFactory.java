package com.everton.loterias.core.usecase.loterias.impl.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LoteriaStrategyFactory {

    private final List<LoteriaStrategy> strategies;

    public LoteriaStrategy getStrategy(String tipoLoteria){
        return strategies.stream()
                .filter(loteria -> loteria.seletorLoteria(tipoLoteria))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Loteria nao localizada"));
    }

}
