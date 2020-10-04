package com.neosuniversity.videolibrary.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosuniversity.videolibrary.entities.TypeMovie;
import com.neosuniversity.videolibrary.repository.TypeMovieRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TypeMovieBusiness implements TypeMovieBusinessI {

	@Autowired
	private TypeMovieRepository typeMovieRepository;

	public void createTypeMovie(TypeMovie typeMovie) {

		log.info("----------------------------------------------");
		log.info("SAVE TYPE MOVIE::::");

		typeMovieRepository.save(typeMovie);
		log.info("----------------------------------------------");
	}

	public TypeMovie readTypeMovie(Long idTypeMovie) {
		log.info("----------------------------------------------");
		log.info("READ TYPE MOVIE::::");

		Optional<TypeMovie> typeMovie = typeMovieRepository.findById(idTypeMovie);

		if (typeMovie.isPresent()) {
			log.info("----------------------------------------------");
			return typeMovie.get();
		} else {
			log.info("Not Found typeMovie: " + idTypeMovie);
			log.info("----------------------------------------------");
			return null;
		}
	}

	public void updateTypeMovie(TypeMovie typeMovie) {
		log.info("----------------------------------------------");
		log.info("UPDATE TYPE MOVIE::::");

		TypeMovie typeMovieRead = readTypeMovie(typeMovie.getIdTypeMovie());

		if (Optional.ofNullable(typeMovieRead).isPresent()) {
			typeMovieRead.setType(typeMovie.getType());

			typeMovieRepository.save(typeMovieRead);
			log.info("----------------------------------------------");
		} else {
			log.info("Not Update typeMovie: " + typeMovie.getType());
			log.info("----------------------------------------------");
		}
	}

	public void deleteTypeMovieTest(Long idTypeMovie) {
		log.info("----------------------------------------------");
		log.info("DELETE TYPE MOVIE::::");

		TypeMovie typeMovie = readTypeMovie(idTypeMovie);

		if (Optional.ofNullable(typeMovie).isPresent()) {
			typeMovieRepository.delete(typeMovie);
			log.info("----------------------------------------------");
		} else {
			log.info("Not Delete TypeMovie: " + idTypeMovie);
			log.info("----------------------------------------------");
		}
	}

	@Override
	public List<TypeMovie> readTypeMovieAll() {
		log.info("----------------------------------------------");
		log.info("GET ALL TYPE MOVIES::::");
		return typeMovieRepository.findAll();
	}

}
