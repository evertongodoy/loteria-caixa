package com.everton.loterias.entrypoint.api.controller.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SalvarApostaRequest {

    private String tipoLoteria;
    private Set<Integer> numerosAposta;
    private boolean ativo;

}
