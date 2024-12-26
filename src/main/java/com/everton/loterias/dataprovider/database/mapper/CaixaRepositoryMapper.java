package com.everton.loterias.dataprovider.database.mapper;

import com.everton.loterias.core.domain.ApostaDomain;
import com.everton.loterias.core.domain.CaixaDomain;
import com.everton.loterias.core.domain.MinhaApostaDomain;
import com.everton.loterias.core.domain.ResultadoDomain;
import com.everton.loterias.dataprovider.database.entity.ApostaEntity;
import com.everton.loterias.dataprovider.database.entity.CaixaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface CaixaRepositoryMapper {

    CaixaRepositoryMapper INSTANCE = Mappers.getMapper(CaixaRepositoryMapper.class);

    @Mapping(source = "resultadoDomain.uuid", target = "uuid")
    @Mapping(source = "resultadoDomain.numeroSorteio", target = "numero")
    @Mapping(source = "resultadoDomain.listaDezenas", target = "dezenas", qualifiedByName = "listToString")
    @Mapping(source = "resultadoDomain.dataApuracao", target = "apuracao")
    @Mapping(source = "resultadoDomain.nomeMunicipioUFSorteio", target = "municipio")
    CaixaEntity toCaixaEntity(final ResultadoDomain resultadoDomain);

    default CaixaDomain toCaixaDomain(final List<CaixaEntity> caixaEntities){
        var resultados = caixaEntities.stream()
                .map(entidade -> ResultadoDomain.builder()
                        .uuid(entidade.getUuid())
                        .numeroSorteio(entidade.getNumero())
                        .tipoJogo(entidade.getTipoJogo())
                        .listaDezenas(
                                Arrays.stream(entidade.getDezenas().split(","))
                                        .map(Integer::parseInt)
                                        .collect(Collectors.toList())
                        )
                        .dataApuracao(entidade.getApuracao())
                        .nomeMunicipioUFSorteio(entidade.getMunicipio())
                        .build())
                .toList();
        return CaixaDomain.builder()
                .resultados(resultados)
                .build();
    }

    @Named("listToString")
    default String listDezenasToString(final List<Integer> dezenas){
        return dezenas.stream()
                .map(String::valueOf) // Converte cada Integer para String
                .collect(Collectors.joining(","));
    }

    default MinhaApostaDomain toMinhasApostasDomain(final List<ApostaEntity> apostasEntity){
        var domains =  apostasEntity.stream()
                .map(apostaEntity -> ApostaDomain.builder()
                        .id(apostaEntity.getId())
                        .uuid(apostaEntity.getUuid())
                        .tipoJogo(apostaEntity.getTipoJogo())
                        .numeros(this.stringDezenasToList(apostaEntity.getNumeros()))
                        .inicio(apostaEntity.getInicio())
                        .ativo(apostaEntity.isAtivo())
                        .build())
                .toList();
        return MinhaApostaDomain.builder()
                .apostasDomain(domains)
                .build();
    }

    @Named("stringToList")
    default List<Integer> stringDezenasToList(final String dezenas){
        return Arrays.stream(dezenas.split(","))
                .map(Integer::parseInt) // Converte cada elemento para Integer
                .collect(Collectors.toList()); // Coleta como uma lista
    }

}