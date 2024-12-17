package com.everton.loterias.core.usecase.loterias.impl.strategy.impl;

import com.everton.loterias.core.domain.LoteriaDomain;
import com.everton.loterias.core.gateway.client.LoteriaGatewayWeb;
import com.everton.loterias.core.gateway.database.LoteriaGateway;
import com.everton.loterias.core.usecase.loterias.impl.strategy.LoteriaStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LotomaniaStrategyImpl implements LoteriaStrategy {

    private final LoteriaGateway loteriaGateway;
    private final LoteriaGatewayWeb loteriaGatewayWeb;
    private static final String LOTOMANIA = "Lotomania";

    @Override
    public boolean seletorLoteria(String tipoLoteria) {
        return LOTOMANIA.equals(tipoLoteria);
    }

    @Override
    public LoteriaDomain salvarAposta(final LoteriaDomain domain) {
        validarTamanhoLista(domain.getNumeros());
        return loteriaGateway.salvarMinhaLoteria(domain);
    }

    @Override
    public void atualizarBaseLoteria(String tipoLoteria) {
        loteriaGatewayWeb.recuperarUltimoSorteio(tipoLoteria);
    }

    private void validarTamanhoLista(List<Integer> numeros){
        if(Objects.isNull(numeros) || numeros.isEmpty() || numeros.size() < 20){
            throw new RuntimeException("Quantidade minima de numeros deve ser 20");
        }
    }

}