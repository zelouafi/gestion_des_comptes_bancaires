package com.glsid.mabanque.web;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.glsid.mabanque.entities.Client;
import com.glsid.mabanque.entities.Compte;
import com.glsid.mabanque.entities.CompteCourant;
import com.glsid.mabanque.metier.IBanqueMetier;

@Controller
public class ClientController {

	@Autowired
	private IBanqueMetier metier ; 
	
	@RequestMapping(value="client")
	public String index(Model m ,@RequestParam(defaultValue="0") int page,Long numClient 
			,@RequestParam(defaultValue="",name="nem") String nomOrEmail , @RequestParam(defaultValue="0") boolean update 
			, Long numClientUp , String refcompte) {
		Page<Client> cls = metier.listClients(page,nomOrEmail);
		
		try {
		Client client = metier.getClient(numClient);
		List<Compte> comptes = client.getListCompte();
		m.addAttribute("comptes",comptes);
		}
		catch (Exception e) {}
		int []t = new int[cls.getTotalPages()];
		m.addAttribute("pages",t);
		m.addAttribute("currentPage",page);
		m.addAttribute("clients",cls);
		m.addAttribute("nem", nomOrEmail);
		if (update) {
			m.addAttribute("update",true);
			Client client = metier.getClient(numClientUp);
			m.addAttribute("clientUp",client);
		}
		
 		return "clients";
	}
	@RequestMapping(method=RequestMethod.POST, value="/updateClient")
	public String updateClient(@RequestParam Long code,@RequestParam String nom ,@RequestParam String email) {
		Client client = new Client();
		client.setCode(code);
		client.setEmail(email);
		client.setNom(nom);
		metier.modifierClient(client);
		return "redirect:/client?up";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/addClient")
	public String addClient(@RequestParam String nom ,@RequestParam String email) {
		Client client = new Client(nom,email);
		metier.addClient(client);
		return "redirect:/client?mod";
	}
	
	@RequestMapping(value="/deleteClient")
	public String deleteClient(Long numclient) {
		Client client = metier.getClient(numclient);
		metier.deleteClient(client);
		return "redirect:/client";
	}
	
}
