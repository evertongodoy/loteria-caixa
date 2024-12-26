package com.everton.loterias.entrypoint.api.mapper;

import com.everton.loterias.core.domain.*;
import com.everton.loterias.entrypoint.api.controller.response.ApostaCheckedResponse;
import com.everton.loterias.entrypoint.api.controller.response.ApostaResponse;
import com.everton.loterias.entrypoint.api.controller.response.CheckApostasResponse;
import com.everton.loterias.entrypoint.api.controller.response.MinhaApostaResponse;
import com.everton.loterias.entrypoint.api.dto.TipoLoteria;
import jdk.jfr.Name;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface ApostaMapper {

    ApostaMapper INSTANCE = Mappers.getMapper(ApostaMapper.class);

    @Mapping(source = "domain.apostasDomain", target = "apostasResponse", qualifiedByName = "toSalvarApostaResponse")
    @Mapping(source = "domain.apostasDomain", target = "totalApostas", qualifiedByName = "toTotalApostas")
    MinhaApostaResponse toResponse(final MinhaApostaDomain domain);

    @Named("toSalvarApostaResponse")
    default List<ApostaResponse> converterParaResponse(final List<ApostaDomain> apostasDomain) {
        return apostasDomain.stream()
            .map(apostaDomain -> ApostaResponse.builder()
                    .uuid(apostaDomain.getUuid())
                    .tipoLoteria(TipoLoteria.fromDescricao(apostaDomain.getTipoJogo()))
                    .numeros(apostaDomain.getNumeros())
                    .inicio(apostaDomain.getInicio())
                    .ativo(apostaDomain.isAtivo())
                    .build()
            ).collect(Collectors.toList());
    }

    @Named("toTotalApostas")
    default Integer getTotalApostas(final List<ApostaDomain> apostasDomain){
        return apostasDomain.size();
    }

    default CheckApostasResponse toCheckApostaResponse(final CheckApostasDomain checkApostasDomain){
        var apostasCheckedasResponse = checkApostasDomain.getApostasCheckadas().stream()
                .map(apostaChecada -> ApostaCheckedResponse.builder()
                        .numeroSorteio(apostaChecada.getNumeroSorteio())
                        .totalAcertos(apostaChecada.getNumerosCorretos().size())
                        .numerosCorretos(apostaChecada.getNumerosCorretos())
                        .tipoJogo(apostaChecada.getTipoJogo().getDescricao())
                        .build())
                .sorted(Comparator.comparingInt(ApostaCheckedResponse::getTotalAcertos).reversed())
                .toList();
        return CheckApostasResponse.builder()
                .totalJogosComAcerto(apostasCheckedasResponse.size())
                .apostasCheckadas(apostasCheckedasResponse)
                .build();
    }

}