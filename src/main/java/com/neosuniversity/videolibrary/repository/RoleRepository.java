package com.neosuniversity.videolibrary.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neosuniversity.videolibrary.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>  {
  
	Set<Role> findByName(String name);
}
