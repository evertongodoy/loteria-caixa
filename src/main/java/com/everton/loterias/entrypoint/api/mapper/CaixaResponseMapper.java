package com.everton.loterias.entrypoint.api.mapper;

import com.everton.loterias.core.domain.AtualizacaoDataBaseDomain;
import com.everton.loterias.core.domain.CaixaDomain;
import com.everton.loterias.entrypoint.api.controller.response.AtualizacaoDataBaseResponse;
import com.everton.loterias.entrypoint.api.controller.response.CaixaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CaixaResponseMapper {

    CaixaResponseMapper INSTANCE = Mappers.getMapper(CaixaResponseMapper.class);

    @Mapping(target = "totalResultados", source = "caixaDomain", qualifiedByName = "getTotalResultados")
    CaixaResponse toCaixaResponse(final CaixaDomain caixaDomain);

    AtualizacaoDataBaseResponse toDataBaseResponse(final AtualizacaoDataBaseDomain atualizacaoDataBaseDomain);

    @Named(value = "getTotalResultados")
    default Integer totalResultados(final CaixaDomain caixaDomain){
        return caixaDomain.getResultados().size();
    }

}