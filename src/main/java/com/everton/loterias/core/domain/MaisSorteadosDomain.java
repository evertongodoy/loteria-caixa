package com.everton.loterias.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaisSorteadosDomain {

    private Integer totalLoterias;
    private Map<Integer, Integer> numerosMaisSorteados;
    private Map<Integer, Double> porcentagemSaida;

}
