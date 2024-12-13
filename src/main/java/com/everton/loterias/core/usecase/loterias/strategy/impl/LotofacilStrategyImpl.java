package com.everton.loterias.core.usecase.loterias.strategy.impl;

import com.everton.loterias.core.usecase.loterias.strategy.LoteriaStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotofacilStrategyImpl implements LoteriaStrategy {

    @Override
    public boolean seletorLoteria(String tipoLoteria) {
        return "Lotofacil".equals(tipoLoteria);
    }

    @Override
    public void salvarMinhaLoteria(String tipoLoteria, List<Integer> numeros) {
        System.out.println("Lotofacil aqui");
    }

}
