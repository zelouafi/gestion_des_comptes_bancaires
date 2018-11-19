package com.glsid.mabanque.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.glsid.mabanque.entities.Client;

public interface ClientRepository extends JpaRepository<Client,Long>{

	@Query("select c from Client c where c.nom like :nem or c.email like :nem ")
	public Page<Client> findClientsByNameOrEmail(@Param("nem") String nem , Pageable pageable);
	
}
