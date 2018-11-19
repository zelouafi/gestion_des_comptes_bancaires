package com.glsid.mabanque.metier;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.glsid.mabanque.dao.ClientRepository;
import com.glsid.mabanque.dao.CompteRepository;
import com.glsid.mabanque.dao.OperationRepository;
import com.glsid.mabanque.entities.Client;
import com.glsid.mabanque.entities.Compte;
import com.glsid.mabanque.entities.CompteCourant;
import com.glsid.mabanque.entities.Operation;
import com.glsid.mabanque.entities.Retrait;
import com.glsid.mabanque.entities.Versement;

@Transactional
@Service
public class MetierBanqueImplem implements IBanqueMetier {

	@Autowired
	private CompteRepository compteRepository;
	@Autowired
	private OperationRepository operationRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Override
	public Compte consultationCompte(String numCompte) {
		Optional<Compte> compte =  compteRepository.findById(numCompte);
		if (!compte.isPresent()) throw new RuntimeException("compte introuvable");
		return compte.get();
	}
	
	@Override
	public void retirer(String numcompte, double montant) {
		Compte compte = compteRepository.getOne(numcompte);
		double decouvert = 0 ;
		if (compte instanceof CompteCourant ) 
			decouvert = ((CompteCourant) compte).getDecouvert();
		
		if (compte.getSolde()+decouvert-montant<0) {
			throw new RuntimeException("solde insuffisant");
		}
		Retrait op = new Retrait(new Date(), montant,compte);
		operationRepository.save(op);
		compte.setSolde(compte.getSolde()-montant);
		compteRepository.save(compte);
	}

	@Override
	public void verser(String numcompte, double montant) {
		Compte compte = compteRepository.getOne(numcompte);
		Versement versement = new Versement(new Date(), montant, compte);
		operationRepository.save(versement);
		compte.setSolde(compte.getSolde()+montant);
		compteRepository.save(compte);		
	}

	@Override
	public void virement(String numcompte1, String numcompte2, double montant) {
		if (numcompte1.equals(numcompte2)) throw new RuntimeException("Impossible de faire le virement sur le meme compte");
		retirer(numcompte1, montant);
		verser(numcompte2, montant);	
	}

	@Override
	public Page<Operation> listOperationSortByDate(String numcompte , int page , int size) {
		Compte compte = compteRepository.getOne(numcompte); 	
		Pageable pageable = PageRequest.of(page, size, Direction.DESC, "dateOperation");
		Page<Operation> ops = operationRepository.findByCompte(compte,pageable);
		return ops ;
	}
	

	@Override
	public Page<Client> listClients(int page,String nem) {
		return clientRepository.findClientsByNameOrEmail("%"+nem+"%", PageRequest.of(page, 5) );
	}
	@Override
	public Client getClient(Long id) {
		return clientRepository.getOne(id);
	}

	@Override
	public void modifierClient(Client client) {
		clientRepository.save(client);
	}

	@Override
	public void deleteClient(Client client) {
		clientRepository.delete(client);
		
	}
	
	@Override
	public void addClient(Client client) {
		clientRepository.save(client);
		
	}

	@Override
	public void addCompte(Compte compte) {
		compteRepository.save(compte);
		
	}
}
