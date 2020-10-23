package com.neosuniversity.videolibrary.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.neosuniversity.videolibrary.entities.Role;
import com.neosuniversity.videolibrary.entities.User;
import com.neosuniversity.videolibrary.repository.RoleRepository;
import com.neosuniversity.videolibrary.repository.UserRepository;

@Service
public class UserServiceImpl {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	public void saveNewUser(User user) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPwd  = encoder.encode(user.getPassword());
		user.setPassword(encodedPwd);
		
		Set<Role> roles = roleRepo.findByName("ROLE_USER");
		
		user.setRoles(roles);
		
		userRepo.save(user);
	}

}