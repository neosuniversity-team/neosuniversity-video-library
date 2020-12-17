package com.neosuniversity.videolibrary.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class LoadSecurityData implements CommandLineRunner {


	@Autowired
	private JdbcTemplate template;

	@Override
	public void run(String... args) throws Exception {
		
	    Integer users=	template.queryForObject("Select count(*) from VIDEODB.USER", Integer.class);
	    Integer roles = template.queryForObject("Select count(*) from VIDEODB.ROLE", Integer.class);
	    Integer userroles = template.queryForObject("Select count(*) from VIDEODB.USER_ROLES", Integer.class);
	    
	    if (users==0 && roles == 0 & userroles== 0) {
		
		template.update("INSERT INTO VIDEODB.USER(id,username, password) " + 
				"values (1,'user','$2a$10$LsG62RqmRHnJW9ovYUM.6e/kl7eIeI/6J7lQgq3n.l/H0gYWZ3c4W')");
		
		template.update("INSERT INTO VIDEODB.USER(id,username, password) " + 
				"values (2,'admin','$2a$10$N2kcyD1Us1KCdlqOxy04kedoW0UJr68lj/yGjvBCFviyM0BJ2/Ijm')");
		
		template.update("INSERT INTO VIDEODB.ROLE(id, name) " + 
				"values (1, 'ROLE_USER')");

		
		template.update("INSERT INTO VIDEODB.ROLE(id, name) " + 
				"values (2, 'ROLE_ADMIN')");

		
		template.update("INSERT INTO VIDEODB.USER_ROLES (users_id, roles_id)  " + 
				"values (1,1)");
		
		template.update("INSERT INTO VIDEODB.USER_ROLES (users_id, roles_id) " + 
				"values (2,2)");
		
	}
	}

}
