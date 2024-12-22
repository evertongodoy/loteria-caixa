package com.everton.loterias.core.domain;

import lombok.Getter;

@Getter
public enum TipoLoteriaDomain {

    LOTOFACIL("LOTOFACIL"),
    LOTOMANIA("LOTOMANIA"),
    MEGASENA("MEGASENA");

    private final String descricao; // Campo adicional para descrição amigável

    // Construtor explícito para o enum
    TipoLoteriaDomain(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }

    // Metodo estático para recuperar o enum pelo texto/descrição
    public static TipoLoteriaDomain fromDescricao(String descricao) {
        for (TipoLoteriaDomain tipoLoteriaDomain : TipoLoteriaDomain.values()) {
            if (tipoLoteriaDomain.getDescricao().equalsIgnoreCase(descricao)) {
                return tipoLoteriaDomain;
            }
        }
        throw new IllegalArgumentException("Nenhum Tipo de Loteria encontrado para a descrição: " + descricao);
    }

}