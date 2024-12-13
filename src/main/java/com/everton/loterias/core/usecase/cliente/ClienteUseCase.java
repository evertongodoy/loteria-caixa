package com.everton.loterias.core.usecase.cliente;

import com.everton.loterias.core.domain.Cliente;
import com.everton.loterias.core.domain.Clientes;

public interface ClienteUseCase {

    Cliente getClienteById(String id);

    Clientes getClientesFromWeb();

    void criarCliente(Cliente cliente);

    Clientes getTodosClientesDB();

}