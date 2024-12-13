package com.everton.loterias.entrypoint.api.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public enum TipoLoteria {
    LOTOFACIL("Lotofacil"),
    LOTOMANIA("Lotomania"),
    MEGASENA("Megasena");

    private final String descricao; // Campo adicional para descrição amigável

    // Construtor explícito para o enum
    TipoLoteria(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }

}