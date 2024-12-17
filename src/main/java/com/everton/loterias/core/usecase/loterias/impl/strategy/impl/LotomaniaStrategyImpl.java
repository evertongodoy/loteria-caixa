package com.everton.loterias.core.usecase.loterias.strategy.impl;

import com.everton.loterias.core.domain.LoteriaDomain;
import com.everton.loterias.core.gateway.database.LoteriaGateway;
import com.everton.loterias.core.usecase.loterias.strategy.LoteriaStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LotomaniaStrategyImpl implements LoteriaStrategy {

    private final LoteriaGateway loteriaGateway;
    private static final String LOTOMANIA = "Lotomania";

    @Override
    public boolean seletorLoteria(String tipoLoteria) {
        return LOTOMANIA.equals(tipoLoteria);
    }

    @Override
    public void salvarMinhaLoteria(String tipoLoteria, List<Integer> numeros) {
        validarTamanhoLista(numeros);
        var domain = LoteriaDomain.builder()
                .numeros(numeros)
                .build();
        loteriaGateway.salvarMinhaLoteria(domain);
        System.out.println("Lotofacil aqui " + numeros.toString());
    }

    private void validarTamanhoLista(List<Integer> numeros){
        if(Objects.isNull(numeros) || numeros.isEmpty() || numeros.size() < 20){
            throw new RuntimeException("Quantidade minima de numeros deve ser 20");
        }
    }

}
