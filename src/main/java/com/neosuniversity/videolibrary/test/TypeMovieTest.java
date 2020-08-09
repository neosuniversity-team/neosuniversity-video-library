package com.neosuniversity.videolibrary.test;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosuniversity.videolibrary.entities.TypeMovie;
import com.neosuniversity.videolibrary.repository.TypeMovieRepository;
import com.neosuniversity.videolibrary.util.TypeMovieUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TypeMovieTest {

	@Autowired
	private TypeMovieRepository typeMovieRepository;

	public void createTypeMovieTest() {

		log.info("----------------------------------------------");
		log.info("SAVE TYPE MOVIE::::");

		TypeMovie typeMovie = TypeMovieUtil.createTypeMovieMockup();

		typeMovieRepository.save(typeMovie);
		log.info("----------------------------------------------");
	}

	public TypeMovie readTypeMovieTest(Long idTypeMovie) {
		log.info("----------------------------------------------");
		log.info("READ TYPE MOVIE::::");

		Optional<TypeMovie> typeMovie = typeMovieRepository.findById(idTypeMovie);

		if (typeMovie.isPresent()) {
			log.info(typeMovie.toString());
			log.info("----------------------------------------------");
			return typeMovie.get();
		} else {
			log.info("Not Found typeMovie: " + idTypeMovie);
			log.info("----------------------------------------------");
			return null;
		}
	}

	public void updateTypeMovieTest(Long idTypeMovie,String type) {
		log.info("----------------------------------------------");
		log.info("UPDATE TYPE MOVIE::::");

		TypeMovie typeMovie = readTypeMovieTest(idTypeMovie);
		typeMovie.setType(type);

		if (Optional.ofNullable(typeMovie).isPresent()) {
			typeMovieRepository.save(typeMovie);
			log.info("----------------------------------------------");
			readTypeMovieTest(idTypeMovie);
		} else {
			log.info("Not Update typeMovie: " + idTypeMovie);
			log.info("----------------------------------------------");
		}
	}

	public void deleteTypeMovieTest(Long idTypeMovie) {
		log.info("----------------------------------------------");
		log.info("DELETE MOVIE::::");

		TypeMovie typeMovie = readTypeMovieTest(idTypeMovie);

		if (Optional.ofNullable(typeMovie).isPresent()) {
			typeMovieRepository.delete(typeMovie);
			log.info("----------------------------------------------");
		} else {
			log.info("Not Delete TypeMovie: " + idTypeMovie);
			log.info("----------------------------------------------");
		}
	}
}
