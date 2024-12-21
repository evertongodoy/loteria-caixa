package com.everton.loterias.entrypoint.api.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtualizacaoSorteioResponse {

    private Integer totalElementos;
    private List<SorteioResponse> sorteiosResponse;

}