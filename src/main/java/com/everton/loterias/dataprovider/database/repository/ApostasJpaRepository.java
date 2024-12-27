package com.everton.loterias.dataprovider.database.repository;

import com.everton.loterias.dataprovider.database.entity.ApostaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface ApostasJpaRepository extends JpaRepository<ApostaEntity, Long> {

    List<ApostaEntity> findAllByTipoJogoOrderByInicioAsc(final String tipoJogo);
    List<ApostaEntity> findByUuidAndTipoJogo(final UUID uuid, final String tipoJogo);

    @Transactional
    void deleteByUuid(final UUID uuid);

}