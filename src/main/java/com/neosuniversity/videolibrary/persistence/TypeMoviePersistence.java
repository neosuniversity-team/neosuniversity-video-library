package com.neosuniversity.videolibrary.persistence;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neosuniversity.videolibrary.entities.TypeMovie;
import com.neosuniversity.videolibrary.repository.TypeMovieRepository;

@Repository
public class TypeMoviePersistence {
	
	@Autowired
	private TypeMovieRepository typeMovieRepository;
	
	
	public void createTypeMovie(TypeMovie typeMovie) {
		typeMovieRepository.save(typeMovie);
	}

	public Optional<TypeMovie> readTypeMovieById(Long idTypeMovie) {
		return typeMovieRepository.findById(idTypeMovie);
	}

	public void updateTypeMovie(TypeMovie typeMovie) {
		typeMovieRepository.save(typeMovie);
	}

	public void deleteTypeMovie(TypeMovie typeMovie) {
		typeMovieRepository.delete(typeMovie);
	}

}
