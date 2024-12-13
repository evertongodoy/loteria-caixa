package com.everton.loterias.dataprovider.client.gateway;

import com.everton.loterias.core.domain.Clientes;
import com.everton.loterias.core.gateway.client.ClienteWebData;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class ClienteDataprovider implements ClienteWebData {

    private final WebClient webClient;

    public ClienteDataprovider(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Clientes getAllWeb() {
        String url = "https://run.mocky.io/v3/849bc20f-18de-421d-b723-af1237d6fc15";
        return webClient.get()
                .uri(url)
                .retrieve()
                .bodyToMono(Clientes.class).block();
    }

}