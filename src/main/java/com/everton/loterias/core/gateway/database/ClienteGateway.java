package com.everton.loterias.core.gateway.database;

import com.everton.loterias.core.domain.Cliente;
import com.everton.loterias.core.domain.Clientes;

import java.util.Optional;

public interface ClienteGateway {

    Optional<Cliente> findById(String id);
    void criarCliente(Cliente cliente);
    Clientes findAllDatabase();

}