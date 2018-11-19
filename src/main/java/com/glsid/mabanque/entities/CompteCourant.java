package com.glsid.mabanque.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;

@Entity
@DiscriminatorValue("cc")
public class CompteCourant extends Compte {
 
	private double decouvert;
	
	
	public CompteCourant() {
		super();
	}

	public CompteCourant(Date dateCreation, double solde, Client client ,
			double decouvert) {
		super(dateCreation, solde, client);
		this.decouvert = decouvert;
	}

	public double getDecouvert() {
		return decouvert;
	}

	public void setDecouvert(double decouvert) {
		this.decouvert = decouvert;
	}

	
	
}
