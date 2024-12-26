package com.everton.loterias.dataprovider.database.repository;

import com.everton.loterias.dataprovider.database.entity.CaixaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CaixaJpaRepository extends JpaRepository<CaixaEntity, Long> {

    List<CaixaEntity> findAllByTipoJogoOrderByNumeroAsc(final String tipoJogo);

    List<CaixaEntity> findByNumeroAndTipoJogo(final Integer numero, final String tipoJogo);

}
