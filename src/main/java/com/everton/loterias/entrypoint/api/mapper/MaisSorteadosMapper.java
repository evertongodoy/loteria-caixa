package com.everton.loterias.entrypoint.api.mapper;

import com.everton.loterias.core.domain.MaisSorteadosDomain;
import com.everton.loterias.entrypoint.api.controller.response.MaisSorteadosResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MaisSorteadosMapper {

    MaisSorteadosMapper INSTANCE = Mappers.getMapper(MaisSorteadosMapper.class);

    MaisSorteadosResponse toMaisSorteadosResponse(final MaisSorteadosDomain maisSorteadosDomain);

}