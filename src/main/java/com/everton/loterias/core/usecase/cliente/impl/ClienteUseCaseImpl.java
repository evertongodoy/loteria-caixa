package com.everton.loterias.core.usecase.cliente.impl;

import com.everton.loterias.core.domain.Cliente;
import com.everton.loterias.core.domain.Clientes;
import com.everton.loterias.core.exceptions.ClienteNaoEcontradoException;
import com.everton.loterias.core.usecase.cliente.ClienteUseCase;
import com.everton.loterias.core.gateway.database.ClienteGateway;
import com.everton.loterias.core.gateway.client.ClienteWebData;
import com.everton.loterias.dataprovider.database.gateway.ClienteGatewayImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteUseCaseImpl implements ClienteUseCase {

    private final ClienteGateway clienteGateway;
    private final ClienteWebData clienteWebData;

    public ClienteUseCaseImpl(final ClienteGatewayImpl clienteGateway,
                              final ClienteWebData clienteWebData) {
        this.clienteGateway = clienteGateway;
        this.clienteWebData = clienteWebData;
    }

    @Override
    public Cliente getClienteById(String id) {
        Optional<Cliente> clienteOptional = clienteGateway.findById(id);
        return clienteOptional.orElseThrow(() -> new ClienteNaoEcontradoException("Cliente nao encontrado"));
    }

    @Override
    public Clientes getClientesFromWeb() {
        return clienteWebData.getAllWeb();
    }

    @Override
    public void criarCliente(Cliente cliente) {
        clienteGateway.criarCliente(cliente);
    }

    @Override
    public Clientes getTodosClientesDB() {
        return clienteGateway.findAllDatabase();
    }

}