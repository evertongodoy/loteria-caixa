package com.everton.loterias.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApostaCheckedDomain {

    private Integer numeroSorteio;
    private LocalDate dataSorteio;
    private Integer totalAcertos;
    private List<Integer> numerosCorretos;
    private TipoLoteriaDomain tipoJogo;

}