package com.everton.loterias.dataprovider.database.mapper;

import com.everton.loterias.core.domain.LoteriaDomain;
import com.everton.loterias.dataprovider.database.entity.LoteriaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LoteriaRepositoryMapper {

    LoteriaRepositoryMapper INSTANCE = Mappers.getMapper(LoteriaRepositoryMapper.class);

    LoteriaEntity toEntity(LoteriaDomain domain);
    LoteriaDomain toDomain(LoteriaEntity entity);

}
