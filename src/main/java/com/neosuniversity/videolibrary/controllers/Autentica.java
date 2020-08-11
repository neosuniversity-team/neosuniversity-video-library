package com.neosuniversity.videolibrary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Autentica {

	@RequestMapping(value="/autentica" , method = RequestMethod.POST)
	public String autentica (@RequestParam String usuario, @RequestParam String password) {
		System.out.println(usuario);
		System.out.println(password);
		
		if (!usuario.equalsIgnoreCase("admin")) {
			return "login";
		}
		return "home";
	}
}
