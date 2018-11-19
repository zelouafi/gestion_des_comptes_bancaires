package com.glsid.mabanque.metier;

import org.springframework.data.domain.Page;

import com.glsid.mabanque.entities.Client;
import com.glsid.mabanque.entities.Compte;
import com.glsid.mabanque.entities.Operation;

public interface IBanqueMetier {

	public Compte consultationCompte(String numCompte);
	public void retirer(String numcompte , double montant);
	public void verser(String numcompte , double montant);
	public void virement(String numcompte1,String numcompte2 , double montant);
	public Page<Client> listClients(int page, String nem);
	public Page<Operation> listOperationSortByDate(String numcompte ,int page , int size);
	public Client getClient(Long id) ;
	public void modifierClient(Client client);
	public void deleteClient(Client client);
	public void addClient(Client client) ;
	public void addCompte (Compte compte);
}
