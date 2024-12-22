package com.everton.loterias.dataprovider.client.gateway;

import com.everton.loterias.core.domain.CaixaDomain;
import com.everton.loterias.core.domain.ResultadoDomain;
import com.everton.loterias.core.domain.TipoLoteriaDomain;
import com.everton.loterias.core.gateway.client.LoteriaGatewayWeb;
//import com.everton.loterias.dataprovider.client.mapper.CaixaClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LoteriaGatewayWebImpl implements LoteriaGatewayWeb {

    private final WebClient webClient;

    @Override
    public CaixaDomain recuperarSorteio(final TipoLoteriaDomain tipoLoteriaDomain,
                                        final Integer numeroSorteio) {
        var url = criarUrl(tipoLoteriaDomain.getDescricao().toLowerCase(), numeroSorteio);
        var response = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(ResultadoDomain.class)
                .block();
        return CaixaDomain.builder()
                .resultados(Objects.isNull(response) ? Collections.emptyList() : List.of(response))
                .build();
    }

    private String criarUrl(final String tipoLoteria, final Integer numeroSorteio){
        return URL.concat("/")
                .concat(tipoLoteria)
                .concat("/")
                .concat(Objects.toString(numeroSorteio, ""));
    }

}



/*
https://servicebus2.caixa.gov.br/portaldeloterias/api/quina
https://servicebus2.caixa.gov.br/portaldeloterias/api/megasena
https://servicebus2.caixa.gov.br/portaldeloterias/api/duplasena
https://servicebus2.caixa.gov.br/portaldeloterias/api/lotofacil
https://servicebus2.caixa.gov.br/portaldeloterias/api/lotomania
https://servicebus2.caixa.gov.br/portaldeloterias/api/diadesorte
https://servicebus2.caixa.gov.br/portaldeloterias/api/timemania
https://servicebus2.caixa.gov.br/portaldeloterias/api/federal
https://servicebus2.caixa.gov.br/portaldeloterias/api/loteca
https://servicebus2.caixa.gov.br/portaldeloterias/api/supersete

P.ex., para se obter o resultado do concurso 1234 da quina, basta um GET em

https://servicebus2.caixa.gov.br/portaldeloterias/api/quina/1234
 */