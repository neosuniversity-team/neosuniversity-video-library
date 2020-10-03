package com.neosuniversity.videolibrary.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.neosuniversity.videolibrary.entities.User;

public class PasswordValidator  implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;

		//Solo hace la comparacion si los dos campos no estan vacios
	      if(!user.getPassword().isEmpty() && !user.getPasswordConfirm().isEmpty() &&
	    		  !user.getPassword().equals(user.getPasswordConfirm())) {
	          errors.rejectValue("password", null, "Los passwords no coinciden");
	      }

	}

}
