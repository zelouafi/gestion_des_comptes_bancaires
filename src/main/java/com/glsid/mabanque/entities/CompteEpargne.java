package com.glsid.mabanque.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CE")
public class CompteEpargne extends Compte{

	private double taux ;

	public CompteEpargne() {
		super();
	}
	public CompteEpargne( Date dateCreation, double solde, Client client, double taux) {
		super(dateCreation, solde, client);
		this.taux = taux;
	}

	public double getTaux() {
		return taux;
	}

	public void setTaux(double taux) {
		this.taux = taux;
	} 
	
	
}
