package com.everton.loterias.core.usecase.loteriasfront;

import java.util.List;

public interface LoteriaFrontUsecase {

    List<Integer> numerosDisponiveis(String tipoLoteria);
    List<Integer> convertNumerosSelecionados(String numeros);
    Integer quantidadeMinimaNumerosJogo(String tipoLoteria);

}
