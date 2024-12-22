package com.everton.loterias.core.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultadoDomain {

    @JsonProperty(value = "id")
    private Long id;
    @JsonProperty(value = "uuid")
    private UUID uuid;
    @JsonProperty(value = "numero")
    private Integer numeroSorteio;
    @JsonProperty(value = "tipoJogo")
    private String tipoJogo;
    @JsonProperty(value = "listaDezenas")
    private List<Integer> listaDezenas;
    @JsonProperty(value = "dataApuracao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dataApuracao;
    @JsonProperty(value = "nomeMunicipioUFSorteio")
    private String nomeMunicipioUFSorteio;

}