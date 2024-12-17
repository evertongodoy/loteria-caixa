package com.everton.loterias.entrypoint.api.mapper;

import com.everton.loterias.core.domain.LoteriaDomain;
import com.everton.loterias.entrypoint.api.controller.response.SalvarApostaResponse;
import com.everton.loterias.entrypoint.api.dto.TipoLoteria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Objects;

@Mapper
public interface LoteriaMapper {

    LoteriaMapper INSTANCE = Mappers.getMapper(LoteriaMapper.class);

    @Mapping(source = "domain.tipoJogo", target = "tipoLoteria", qualifiedByName = "toTipoLoteria")
    SalvarApostaResponse toResponse(final LoteriaDomain domain);

    @Named("toTipoLoteria")
    default TipoLoteria converterToTipoLoteria(final String tipoLoteria) {
        if (Objects.isNull(tipoLoteria) || tipoLoteria.isEmpty()) {
            return null;
        }
        return TipoLoteria.fromDescricao(tipoLoteria);
    }

}
