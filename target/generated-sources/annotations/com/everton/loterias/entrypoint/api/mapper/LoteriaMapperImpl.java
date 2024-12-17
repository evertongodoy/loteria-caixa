package com.everton.loterias.entrypoint.api.mapper;

import com.everton.loterias.core.domain.LoteriaDomain;
import com.everton.loterias.entrypoint.api.controller.response.SalvarApostaResponse;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-12-17T15:26:35-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.4 (Amazon.com Inc.)"
)
public class LoteriaMapperImpl implements LoteriaMapper {

    @Override
    public SalvarApostaResponse toResponse(LoteriaDomain domain) {
        if ( domain == null ) {
            return null;
        }

        SalvarApostaResponse.SalvarApostaResponseBuilder salvarApostaResponse = SalvarApostaResponse.builder();

        salvarApostaResponse.tipoLoteria( converterToTipoLoteria( domain.getTipoJogo() ) );
        salvarApostaResponse.uuid( domain.getUuid() );
        List<Integer> list = domain.getNumeros();
        if ( list != null ) {
            salvarApostaResponse.numeros( new ArrayList<Integer>( list ) );
        }
        salvarApostaResponse.inicio( domain.getInicio() );
        salvarApostaResponse.ativo( domain.isAtivo() );

        return salvarApostaResponse.build();
    }
}
