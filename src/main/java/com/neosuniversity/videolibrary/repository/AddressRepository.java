package com.neosuniversity.videolibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neosuniversity.videolibrary.entities.Address;;

public interface AddressRepository extends JpaRepository <Address, Long> {

}
