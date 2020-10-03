package com.neosuniversity.videolibrary.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.neosuniversity.videolibrary.entities.User;
import com.neosuniversity.videolibrary.services.UserServiceImpl;
import com.neosuniversity.videolibrary.validators.PasswordValidator;

@Controller
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@GetMapping("/signup")
	public String signup(ModelMap model) {
		
		model.addAttribute("newUser",new User());
		
		
		return "signup";
		
	}
	
	@PostMapping("/signup")
	public String saveNewUser(@ModelAttribute("newUser") @Valid User usuario,BindingResult result) {
		
        PasswordValidator passwordValidator = new PasswordValidator();
        passwordValidator.validate(usuario, result);
		
		if (result.hasErrors()) {
			return "signup";
		}
		
		userService.saveNewUser(usuario);
			
		return "successSignup";
		
	}

}
