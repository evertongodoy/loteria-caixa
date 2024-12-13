package com.everton.loterias.dataprovider.database.gateway;

import com.everton.loterias.core.domain.LoteriaDomain;
import com.everton.loterias.core.gateway.database.LoteriaGateway;
import com.everton.loterias.dataprovider.database.mapper.LoteriaRepositoryMapper;
import com.everton.loterias.dataprovider.database.repository.LoteriaJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoteriaGatewayImpl implements LoteriaGateway {

    private final LoteriaJpaRepository loteriaJpaRepository;

    @Override
    public void salvarMinhaLoteria(final LoteriaDomain domain) {
        System.out.println(domain);
        var entity = LoteriaRepositoryMapper.INSTANCE.toEntity(domain);
        loteriaJpaRepository.save()
        System.out.println("Salvando loteria");
    }

}
