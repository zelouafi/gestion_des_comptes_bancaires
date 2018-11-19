package com.glsid.mabanque.securiter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AuthentificationController {

	@RequestMapping(value="/login")
	public String authentification() {
		return "login" ;
	}
	@RequestMapping(value="/")
	public String defaultAction() {
		return "redirect:/index" ;
	}
}
