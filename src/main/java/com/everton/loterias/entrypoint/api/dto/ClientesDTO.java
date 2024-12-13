package com.everton.loterias.entrypoint.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ClientesDTO {

    @JsonProperty("clientes")
    private List<ClienteDTO> clientesDTO;

    public List<ClienteDTO> getClientesDTO() {
        return clientesDTO;
    }

    public void setClientesDTO(List<ClienteDTO> clientesDTO) {
        this.clientesDTO = clientesDTO;
    }

}