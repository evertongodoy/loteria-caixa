package com.everton.loterias.dataprovider.database.mapper;

import com.everton.loterias.core.domain.ApostaDomain;
import com.everton.loterias.dataprovider.database.entity.ApostaEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ApostaRepositoryMapper {

    ApostaRepositoryMapper INSTANCE = Mappers.getMapper(ApostaRepositoryMapper.class);

    @Mapping(source = "domain.numeros", target = "numeros", qualifiedByName = "listToString")
    ApostaEntity toEntity(ApostaDomain domain);

    @Mapping(source = "entity.numeros", target = "numeros", qualifiedByName = "stringToList")
    ApostaDomain toDomain(ApostaEntity entity);

    @Named("listToString")
    default String converterNumeroString(final List<Integer> numeros) {
        if (Objects.isNull(numeros) || numeros.isEmpty()) {
            return null;
        }
        return numeros.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(","));
    }

    @Named("stringToList")
    default List<Integer> converterNumeroList(final String nuwmeros) {
        if (Objects.isNull(nuwmeros) || nuwmeros.trim().isEmpty()) {
            return null;
        }
        return Arrays.stream(nuwmeros.split(","))
                .map(Integer::parseInt) // Converter cada elemento para Integer
                .collect(Collectors.toList());
    }

}
