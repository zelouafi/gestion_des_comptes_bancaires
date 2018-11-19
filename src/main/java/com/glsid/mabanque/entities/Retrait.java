package com.glsid.mabanque.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ret")
public class Retrait  extends Operation{
	
	
	public Retrait() {
		super();
	}
	public Retrait(Date date, double montant, Compte compte) {
		super(date, montant, compte);
	}

}
