package com.glsid.mabanque.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ver")
public class Versement extends Operation {

	public Versement(Date dateOperation, double montant, Compte compte) {
		super(dateOperation, montant, compte);
	}
	
	public Versement() {
		super();
	}
	
	
}
