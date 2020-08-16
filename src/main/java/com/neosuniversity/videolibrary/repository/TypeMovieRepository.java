package com.neosuniversity.videolibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neosuniversity.videolibrary.entities.TypeMovie;

public interface TypeMovieRepository extends JpaRepository<TypeMovie, Long> {

}
