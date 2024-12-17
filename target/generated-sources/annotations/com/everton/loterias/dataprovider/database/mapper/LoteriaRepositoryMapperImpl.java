package com.everton.loterias.dataprovider.database.mapper;

import com.everton.loterias.core.domain.LoteriaDomain;
import com.everton.loterias.dataprovider.database.entity.LoteriaEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-17T15:26:35-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.4 (Amazon.com Inc.)"
)
@Component
public class LoteriaRepositoryMapperImpl implements LoteriaRepositoryMapper {

    @Override
    public LoteriaEntity toEntity(LoteriaDomain domain) {
        if ( domain == null ) {
            return null;
        }

        LoteriaEntity loteriaEntity = new LoteriaEntity();

        loteriaEntity.setNumeros( converterNumeroString( domain.getNumeros() ) );
        loteriaEntity.setUuid( domain.getUuid() );
        loteriaEntity.setTipoJogo( domain.getTipoJogo() );
        loteriaEntity.setInicio( domain.getInicio() );
        loteriaEntity.setAtivo( domain.isAtivo() );

        return loteriaEntity;
    }

    @Override
    public LoteriaDomain toDomain(LoteriaEntity entity) {
        if ( entity == null ) {
            return null;
        }

        LoteriaDomain.LoteriaDomainBuilder loteriaDomain = LoteriaDomain.builder();

        loteriaDomain.numeros( converterNumeroList( entity.getNumeros() ) );
        loteriaDomain.uuid( entity.getUuid() );
        loteriaDomain.tipoJogo( entity.getTipoJogo() );
        loteriaDomain.inicio( entity.getInicio() );
        loteriaDomain.ativo( entity.isAtivo() );

        return loteriaDomain.build();
    }
}
