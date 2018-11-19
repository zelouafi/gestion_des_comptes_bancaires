package com.glsid.mabanque.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.glsid.mabanque.entities.Client;
import com.glsid.mabanque.entities.Compte;
import com.glsid.mabanque.entities.CompteCourant;
import com.glsid.mabanque.entities.CompteEpargne;
import com.glsid.mabanque.entities.Operation;
import com.glsid.mabanque.entities.Retrait;
import com.glsid.mabanque.entities.Versement;
import com.glsid.mabanque.metier.IBanqueMetier;
import com.glsid.mabanque.metier.MetierBanqueImplem;

@Controller
public class CompteController {

	@Autowired
	private IBanqueMetier iBanqueMetier;
	
	@RequestMapping(value="/index")
	public String index() {
		return "redirect:/compte";
	}
	@RequestMapping(value="/compte")
	public String consult() {
		return "compte";
	}
	
	@RequestMapping(value="/consultcompte")
	public String consultcompte(Model m , @RequestParam(name="numCompte") String numcompte 
			, @RequestParam(name="page" , defaultValue="0") int page) {
		m.addAttribute("numCompte",numcompte);
		try {
		Compte compte = iBanqueMetier.consultationCompte(numcompte);
		Page<Operation>  op = iBanqueMetier.listOperationSortByDate(numcompte,page,4);
		int t[] = new int[op.getTotalPages()]; 
		m.addAttribute("pages", t);
		m.addAttribute("currentPage", page);
		m.addAttribute("operations",op);
		m.addAttribute("compte",compte);
		}
		catch(RuntimeException e) {
			m.addAttribute("exception",e); 
		}
		return "compte";
	}
	
	@RequestMapping(value="/saveOperation")
	public String saveop(@RequestParam String numero , @RequestParam String operation ,
						 @RequestParam String compte2 , @RequestParam double montant  ) {
		try {
		if (operation.equals("ver")) {
			iBanqueMetier.verser(numero, montant);
		}
		else if (operation.equals("ret")) {
			iBanqueMetier.retirer(numero, montant);
		}
		else if (operation.equals("vir")) {
			iBanqueMetier.virement(numero, compte2, montant);
		}
		}
		catch (Exception e) {
			return "redirect:/consultcompte?numCompte="+numero+"&message="+e.getMessage();

		}
		return "redirect:/consultcompte?numCompte="+numero;
	}
	

	@RequestMapping(value="/addCompte")
	public String ajouteCompte(@RequestParam String numClient , Model m) {
		m.addAttribute("numClient",numClient);
		return "ajouterCompte";
	}
	@RequestMapping(value="/confirmCompte")
	public String confirmCompte(@RequestParam Long numClient,@RequestParam double solde , @RequestParam String type ,@RequestParam(defaultValue="0") double taux ,@RequestParam(defaultValue="0") double decouvert ) {
		if (type.equals("cc")) {
			Client client = iBanqueMetier.getClient(numClient);
			CompteCourant compte = new CompteCourant(new Date(), solde, client, decouvert);
			iBanqueMetier.addCompte(compte);
		}
		else if (type.equals("ce")){
			Client client = iBanqueMetier.getClient(numClient);
			CompteEpargne compte = new CompteEpargne( new Date(), solde, client, taux);
			iBanqueMetier.addCompte(compte);
		}
		
		return "redirect:/client?numClient="+numClient;
	}
}
