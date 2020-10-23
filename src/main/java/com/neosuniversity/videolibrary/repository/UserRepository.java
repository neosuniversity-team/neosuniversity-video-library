
package com.neosuniversity.videolibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neosuniversity.videolibrary.entities.User;

public interface UserRepository extends JpaRepository<User,Long>{
	
	public User findByUsername(String username);
	
}
