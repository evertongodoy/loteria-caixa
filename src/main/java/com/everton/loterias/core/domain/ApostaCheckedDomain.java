package com.everton.loterias.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApostaCheckedDomain {

    private Integer numeroSorteio;
    private Integer totalAcertos;
    private List<Integer> numerosCorretos;
    private TipoLoteriaDomain tipoJogo;

}