package com.everton.loterias.core.domain;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApostaDomain {

    private Long id;
    private UUID uuid;
    private String tipoJogo;
    private List<Integer> numeros;
    private LocalDate inicio;
    private boolean ativo;

}