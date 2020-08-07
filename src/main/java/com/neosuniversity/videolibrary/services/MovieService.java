package com.neosuniversity.videolibrary.services;

import java.util.Optional;

import com.neosuniversity.videolibrary.entities.Movie;

public interface MovieService {
	
	public void save(Movie movie) ;
	
	public Optional<Movie> findById(Long id);
	
	public void  delete(Movie movie) ;

}
