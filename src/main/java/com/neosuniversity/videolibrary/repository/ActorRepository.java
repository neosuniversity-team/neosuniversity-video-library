package com.neosuniversity.videolibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neosuniversity.videolibrary.entities.Actor;


public interface ActorRepository extends JpaRepository <Actor, Long>{

}
