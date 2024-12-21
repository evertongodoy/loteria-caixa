package com.everton.loterias.dataprovider.database.repository;

import com.everton.loterias.dataprovider.database.entity.SorteioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface SorteiosJpaRepository extends JpaRepository<SorteioEntity, Long> {

    Optional<SorteioEntity> findByNumero(Integer numero);

    @Query("SELECT a FROM SorteioEntity a WHERE a.tipoJogo = :nomeLoteria and a.numero = (SELECT MAX(m.numero) FROM SorteioEntity m where m.tipoJogo = :nomeLoteria)")
    Optional<SorteioEntity> findLastByNumero(@Param("nomeLoteria") String tipoJogo);

    Optional<SorteioEntity> findByNumeroAndTipoJogo(final Integer numero, final String tipoJogo);

}
