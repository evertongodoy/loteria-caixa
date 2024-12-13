package com.everton.loterias.core.usecase.loterias.strategy;

import java.util.List;

public interface LoteriaStrategy {

    boolean seletorLoteria(String tipoLoteria);
    void salvarMinhaLoteria(String tipoLoteria, List<Integer> numeros);

}
