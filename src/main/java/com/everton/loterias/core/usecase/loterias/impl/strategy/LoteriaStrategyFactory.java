package com.everton.loterias.core.usecase.loterias.impl.strategy;

import com.everton.loterias.core.domain.TipoLoteriaDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LoteriaStrategyFactory {

    private final List<LoteriaStrategy> strategies;

    public LoteriaStrategy getStrategy(final TipoLoteriaDomain tipoLoteriaDomain){
        return strategies.stream()
                .filter(loteria -> loteria.seletorLoteria(tipoLoteriaDomain))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Loteria nao localizada"));
    }

}
