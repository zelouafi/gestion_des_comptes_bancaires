package com.glsid.mabanque.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.glsid.mabanque.entities.Client;
import com.glsid.mabanque.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte,String> {

	
}
