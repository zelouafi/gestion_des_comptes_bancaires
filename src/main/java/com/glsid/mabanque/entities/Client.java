package com.glsid.mabanque.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Client implements Serializable {

	@Id @GeneratedValue
	private Long code ; 
	private String nom ;
	private String email ;
	@OneToMany(mappedBy="client" , fetch = FetchType.LAZY)
	private List<Compte> listCompte;
	
	public Client() {
		
	}
	public Client(String nom, String email) {
		this.nom = nom;
		this.email = email;
	}
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Compte> getListCompte() {
		return listCompte;
	}
	public void setListCompte(List<Compte> listCompte) {
		this.listCompte = listCompte;
	}
	
	
	
	
	
}
