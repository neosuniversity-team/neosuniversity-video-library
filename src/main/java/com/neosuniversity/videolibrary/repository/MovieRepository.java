package com.neosuniversity.videolibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neosuniversity.videolibrary.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {


}
