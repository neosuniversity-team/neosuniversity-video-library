package com.neosuniversity.videolibrary.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosuniversity.videolibrary.entities.Movie;
import com.neosuniversity.videolibrary.repository.MovieRepository;

@Service
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	private MovieRepository repository;
	
	
	public void save(Movie movie) {
		repository.save(movie);
	}
	
	public Optional<Movie> findById(Long id) {
		return repository.findById(id);
	}
	
	public void  delete(Movie movie) {
		repository.delete(movie);
	}


}
