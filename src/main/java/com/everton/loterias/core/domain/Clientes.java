package com.everton.loterias.core.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Clientes {

    @JsonProperty("clientes")
    private List<Cliente> clientes;

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

}