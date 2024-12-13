package com.everton.loterias.dataprovider.database.repository;

import com.everton.loterias.dataprovider.database.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, String> {

}