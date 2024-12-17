package com.everton.loterias.entrypoint.api.mapper;

import com.everton.loterias.core.domain.Cliente;
import com.everton.loterias.entrypoint.api.dto.ClienteDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-17T15:26:35-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.4 (Amazon.com Inc.)"
)
@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public Cliente toCliente(ClienteDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setId( dto.getId() );
        cliente.setNome( dto.getNome() );

        return cliente;
    }

    @Override
    public ClienteDTO toClienteDTO(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        String id = null;
        String nome = null;

        id = cliente.getId();
        nome = cliente.getNome();

        ClienteDTO clienteDTO = new ClienteDTO( id, nome );

        return clienteDTO;
    }
}
