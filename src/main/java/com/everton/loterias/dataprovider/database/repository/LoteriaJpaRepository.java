package com.everton.loterias.dataprovider.database.repository;

import com.everton.loterias.dataprovider.database.entity.LoteriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoteriaJpaRepository extends JpaRepository<LoteriaEntity, Long> {

}