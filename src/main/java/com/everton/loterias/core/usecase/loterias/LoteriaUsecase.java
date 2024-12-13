package com.everton.loterias.core.usecase.loterias;

import java.util.List;

public interface LoteriaUsecase {

    void salvarMinhaLoteria(String tipoLoteria, List<Integer> numeros);

}
