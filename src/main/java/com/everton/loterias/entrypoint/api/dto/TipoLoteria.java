package com.everton.loterias.entrypoint.api.dto;

import lombok.Getter;

@Getter
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

    // Metodo estático para recuperar o enum pelo texto/descrição
    public static TipoLoteria fromDescricao(String descricao) {
        for (TipoLoteria tipoLoteria : TipoLoteria.values()) {
            if (tipoLoteria.getDescricao().equalsIgnoreCase(descricao)) {
                return tipoLoteria;
            }
        }
        throw new IllegalArgumentException("Nenhum Tipo de Loteria encontrado para a descrição: " + descricao);
    }

}