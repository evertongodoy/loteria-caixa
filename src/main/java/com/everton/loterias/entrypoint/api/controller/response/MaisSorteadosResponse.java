package com.everton.loterias.entrypoint.api.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaisSorteadosResponse {

    private Integer totalLoterias;
    private Map<Integer, Integer> numerosMaisSorteados;
    private Map<Integer, Double> porcentagemSaida;

}
