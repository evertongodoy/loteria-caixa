package com.everton.loterias.dataprovider.client.gateway;

import com.everton.loterias.core.domain.Clientes;
import com.everton.loterias.core.gateway.client.LoteriaGatewayWeb;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class LoteriaGatewayWebImpl implements LoteriaGatewayWeb {

    private final WebClient webClient;

    @Override
    public void recuperarUltimoSorteio(String tipoLoteria) {
        String url = "https://servicebus2.caixa.gov.br/portaldeloterias/api/lotomania/2711";
        String ssssss = webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println(ssssss);
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