package com.everton.loterias.dataprovider.database.repository;

import com.everton.loterias.dataprovider.database.entity.ApostaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApostasJpaRepository extends JpaRepository<ApostaEntity, Long> {

}