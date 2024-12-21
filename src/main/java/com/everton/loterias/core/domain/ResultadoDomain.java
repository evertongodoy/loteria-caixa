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
public class LoteriaDomain {

    private LocalDate dataApuracao;
    private List<Integer> listaDezenas;
    private String nomeMunicipioUFSorteio;
    private Integer numero;
    private String tipoJogo;
}
