package com.everton.loterias.dataprovider.database.gateway;

import com.everton.loterias.core.domain.*;
import com.everton.loterias.core.gateway.database.LoteriaGateway;
import com.everton.loterias.dataprovider.database.entity.CaixaEntity;
import com.everton.loterias.dataprovider.database.mapper.ApostaRepositoryMapper;
import com.everton.loterias.dataprovider.database.mapper.CaixaRepositoryMapper;
import com.everton.loterias.dataprovider.database.repository.ApostasJpaRepository;
import com.everton.loterias.dataprovider.database.repository.CaixaJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LoteriaGatewayImpl implements LoteriaGateway {

    private final ApostasJpaRepository apostasJpaRepository;
    private final CaixaJpaRepository sorteiosJpaRepository;

    @Override
    public ApostaDomain salvarMinhaLoteria(final ApostaDomain domain) {
        var entity = ApostaRepositoryMapper.INSTANCE.toEntity(domain);
        return ApostaRepositoryMapper.INSTANCE.toDomain(apostasJpaRepository.save(entity));
    }

    @Override
    public CaixaDomain recuperarSorteio(final TipoLoteriaDomain tipoLoteriaDomain, final Integer numeroSorteio) {
       if(Objects.isNull(numeroSorteio)){
           return CaixaRepositoryMapper.INSTANCE.toCaixaDomain(
                   sorteiosJpaRepository.findAllByTipoJogoOrderByNumeroAsc(tipoLoteriaDomain.getDescricao())
           );
       }
       return CaixaRepositoryMapper.INSTANCE.toCaixaDomain(
               sorteiosJpaRepository.findByNumeroAndTipoJogo(numeroSorteio, tipoLoteriaDomain.getDescricao())
       );
    }

    @Override
    public CaixaDomain salvarSorteio(final ResultadoDomain resultadoDomain) {
        var entity = CaixaRepositoryMapper.INSTANCE.toCaixaEntity(resultadoDomain);
        return CaixaRepositoryMapper.INSTANCE.toCaixaDomain(
                List.of(sorteiosJpaRepository.save(entity))
        );
    }

}