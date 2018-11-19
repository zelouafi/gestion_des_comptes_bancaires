package com.glsid.mabanque.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type",length=3)
public abstract class Operation implements Serializable {
	@Id @GeneratedValue
	private Long numero ;
	private Date dateOperation ; 
	private double montant ;
	@ManyToOne
	@JoinColumn(name="ref_compte")
	private Compte compte ;
	
	public Operation() {
		
	}
	public Operation(Date dateOperation, double montant, Compte compte) {
		this.dateOperation = dateOperation;
		this.montant = montant;
		this.compte = compte;
	}
	public Long getNumero() {
		return numero;
	}
	public void setNumero(Long numero) {
		this.numero = numero;
	}
	public Date getDateOperation() {
		return dateOperation;
	}
	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public Compte getCompte() {
		return compte;
	}
	public void setCompte(Compte compte) {
		this.compte = compte;
	} 
	
	
}
