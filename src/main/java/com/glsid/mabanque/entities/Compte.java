package com.glsid.mabanque.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="type" ,length=2 ,discriminatorType=DiscriminatorType.STRING)
public abstract class Compte implements Serializable{
	
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String numero ;
	private Date dateCreation ; 
	private double solde ;
	@ManyToOne()
	@JoinColumn(name="refClient")
	private Client client ; 
	@OneToMany(mappedBy="compte", fetch=FetchType.LAZY)
	private List<Operation> listOperation ;
	
	
	
	public Compte() {
		
	}
	public Compte( Date dateCreation, double solde, Client client) {
	
		this.dateCreation = dateCreation;
		this.solde = solde;
		this.client = client;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public double getSolde() {
		return solde;
	}
	public void setSolde(double solde) {
		this.solde = solde;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public List<Operation> getListOperation() {
		return listOperation;
	}
	public void setListOperation(List<Operation> listOperation) {
		this.listOperation = listOperation;
	} 
	
	
	
}
