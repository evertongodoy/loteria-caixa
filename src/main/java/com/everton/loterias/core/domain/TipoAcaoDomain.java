package com.everton.loterias.core.domain;

import lombok.Getter;

@Getter
public enum TipoAcaoDomain {

    MAIS_SORTEADOS("MAIS_SORTEADOS"),
    APOSTA_SIMULADA("APOSTA_SIMULADA"),
    ATUALIZAR_BASE_DADOS("ATUALIZAR_BASE");

    private final String acao; // Campo adicional para acao amigável

    // Construtor explícito para o enum
    TipoAcaoDomain(String descricao) {
        this.acao = descricao;
    }

    @Override
    public String toString() {
        return acao;
    }

    // Metodo estático para recuperar o enum pelo texto/descrição
    public static TipoAcaoDomain fromDescricao(String descricao) {
        for (TipoAcaoDomain tipoAcaoDomain : TipoAcaoDomain.values()) {
            if (tipoAcaoDomain.getAcao().equalsIgnoreCase(descricao)) {
                return tipoAcaoDomain;
            }
        }
        throw new IllegalArgumentException("Nenhum Tipo de Acao encontrada para a descrição: " + descricao);
    }

}
