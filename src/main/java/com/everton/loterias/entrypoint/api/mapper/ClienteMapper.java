package com.everton.loterias.entrypoint.api.mapper;

import com.everton.loterias.core.domain.Cliente;
import com.everton.loterias.entrypoint.api.dto.ClienteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "Spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, imports = {
        Cliente.class,
        ClienteDTO.class
})
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    Cliente toCliente(ClienteDTO dto);

    ClienteDTO toClienteDTO(Cliente cliente);


}
