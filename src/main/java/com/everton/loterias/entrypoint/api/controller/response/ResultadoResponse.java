package com.everton.loterias.entrypoint.api.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultadoResponse {

    private Long id;
    private UUID uuid;
    private Integer numeroSorteio;
    private String tipoJogo;
    private List<Integer> listaDezenas;
    private LocalDate dataApuracao;
    private String nomeMunicipioUFSorteio;

}