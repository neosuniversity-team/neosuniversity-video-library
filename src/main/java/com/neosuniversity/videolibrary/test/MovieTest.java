package com.neosuniversity.videolibrary.test;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosuniversity.videolibrary.entities.Movie;
import com.neosuniversity.videolibrary.persistence.MoviePersistence;
import com.neosuniversity.videolibrary.util.MovieUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MovieTest {

	@Autowired
	private MoviePersistence moviePersistence;

	public void createMovieTest() {

		log.info("----------------------------------------------");
		log.info("SAVE MOVIE::::");

		Movie movie = MovieUtil.createMovieMockup();

		moviePersistence.createMovie(movie);
		log.info("----------------------------------------------");
	}

	public Movie readMovieTest(Long idMovie) {
		log.info("----------------------------------------------");
		log.info("READ MOVIE::::");

		Optional<Movie> movie = moviePersistence.readMovieById(idMovie);
		
		if(movie.isPresent()) {
			log.info(movie.toString());
			log.info("----------------------------------------------");
			return movie.get();
		}else {
			log.info("Not Found Movie: "+idMovie);
			log.info("----------------------------------------------");
			return null;
		}
		
		
	}

	public void updateMovieTest(Long idMovie) {

		log.info("----------------------------------------------");
		log.info("UPDATE MOVIE::::");
		
		Movie readMovie = readMovieTest(idMovie);
		Movie updateMovie = MovieUtil.updateMovieMockup();

		if(Optional.ofNullable(readMovie).isPresent()) {
			readMovie.setTitle(updateMovie.getTitle());
			readMovie.setYear(updateMovie.getYear());
			readMovie.setSynopsis(updateMovie.getSynopsis());
			moviePersistence.createMovie(readMovie);
			log.info("----------------------------------------------");
			readMovieTest(idMovie);
		}else {
			log.info("Not Update Movie: "+idMovie);
			log.info("----------------------------------------------");
		}

	}
	public void deleteMovieTest(Long idMovie) {

		log.info("----------------------------------------------");
		log.info("DELETE MOVIE::::");
		
		Movie movie = readMovieTest(idMovie);

		if(Optional.ofNullable(movie).isPresent()) {
			moviePersistence.deleteMovie(movie);
			log.info("----------------------------------------------");
		}else {
			log.info("Not Delete Movie: "+idMovie);
			log.info("----------------------------------------------");
		}

	}

}