package com.everton.loterias.entrypoint.api.controller.request;

import com.everton.loterias.entrypoint.api.dto.TipoLoteria;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class SalvarApostaRequest {

    private TipoLoteria tipoLoteria;
    private Set<Integer> numerosAposta;

}
