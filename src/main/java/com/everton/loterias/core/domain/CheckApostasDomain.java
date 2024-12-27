package com.everton.loterias.core.domain;

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
public class CheckApostasDomain {

    private Integer totalJogosComAcerto;
    private Map<Integer, Long> maioresAcertos;
    private List<ApostaCheckedDomain> apostasCheckadas;

}