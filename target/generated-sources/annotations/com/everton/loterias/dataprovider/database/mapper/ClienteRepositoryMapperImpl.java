package com.everton.loterias.dataprovider.database.mapper;

import com.everton.loterias.core.domain.Cliente;
import com.everton.loterias.dataprovider.database.entity.ClienteEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-17T15:26:35-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.4 (Amazon.com Inc.)"
)
@Component
public class ClienteRepositoryMapperImpl implements ClienteRepositoryMapper {

    @Override
    public ClienteEntity toEntity(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        ClienteEntity clienteEntity = new ClienteEntity();

        clienteEntity.setId( cliente.getId() );
        clienteEntity.setNome( cliente.getNome() );

        return clienteEntity;
    }

    @Override
    public Cliente toCliente(ClienteEntity clienteEntity) {
        if ( clienteEntity == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setId( clienteEntity.getId() );
        cliente.setNome( clienteEntity.getNome() );

        return cliente;
    }
}
