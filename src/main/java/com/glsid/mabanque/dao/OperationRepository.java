package com.glsid.mabanque.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.glsid.mabanque.entities.Operation;
import com.glsid.mabanque.entities.Compte;
import java.util.List;

public interface OperationRepository extends JpaRepository<Operation, Long> {

	public Page<Operation> findByCompte(Compte compte, Pageable page);
}
