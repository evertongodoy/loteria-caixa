package com.everton.loterias.dataprovider.client.mapper;

import com.everton.loterias.core.domain.SorteioDomain;
import com.everton.loterias.dataprovider.client.response.SorteioClientResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SorteioClientMapper {

    SorteioClientMapper INSTANCE = Mappers.getMapper(SorteioClientMapper.class);

    SorteioDomain toDomain(final SorteioClientResponse response);

}