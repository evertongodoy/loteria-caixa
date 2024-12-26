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
public class CheckApostasDomain {

    private Integer totalJogosComAcerto;
    private List<ApostaCheckedDomain> apostasCheckadas;

}