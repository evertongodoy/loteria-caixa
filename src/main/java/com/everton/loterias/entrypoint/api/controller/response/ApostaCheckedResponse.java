package com.everton.loterias.entrypoint.api.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApostaCheckedResponse {

    private Integer numeroSorteio;
    private Integer totalAcertos;
    private List<Integer> numerosCorretos;
    private String tipoJogo;

}
