package com.everton.loterias.entrypoint.api.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CheckApostasResponse {

    private Integer totalJogosComAcerto;
    private Map<Integer, Long> maioresAcertos;
    private List<ApostaCheckedResponse> apostasCheckadas;

}