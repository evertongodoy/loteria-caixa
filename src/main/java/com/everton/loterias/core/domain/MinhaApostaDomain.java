package com.everton.loterias.core.domain;

import lombok.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MinhaApostaDomain {

    List<ApostaDomain> apostasDomain;

}