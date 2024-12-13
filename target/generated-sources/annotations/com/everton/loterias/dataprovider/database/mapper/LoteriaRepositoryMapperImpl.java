package com.everton.loterias.dataprovider.database.mapper;

import com.everton.loterias.core.domain.LoteriaDomain;
import com.everton.loterias.dataprovider.database.entity.LoteriaEntity;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-13T15:06:01-0300",
    comments = "version: 1.6.2, compiler: javac, environment: Java 21.0.4 (Amazon.com Inc.)"
)
public class LoteriaRepositoryMapperImpl implements LoteriaRepositoryMapper {

    @Override
    public LoteriaEntity toEntity(LoteriaDomain domain) {
        if ( domain == null ) {
            return null;
        }

        LoteriaEntity loteriaEntity = new LoteriaEntity();

        return loteriaEntity;
    }

    @Override
    public LoteriaDomain toDomain(LoteriaEntity entity) {
        if ( entity == null ) {
            return null;
        }

        LoteriaDomain loteriaDomain = new LoteriaDomain();

        return loteriaDomain;
    }
}
