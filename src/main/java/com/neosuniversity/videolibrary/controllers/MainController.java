package com.neosuniversity.videolibrary.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/login")
	public String hello() {
		
		return "login";
		
	}
	
	
	@RequestMapping("/accessDenied")
	public String accessDenied() {
		
		return "accessDenied";
		
	}
	
	

}
