package com.neosuniversity.videolibrary.test;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosuniversity.videolibrary.entities.Actor;
import com.neosuniversity.videolibrary.entities.Movie;
import com.neosuniversity.videolibrary.entities.TypeMovie;
import com.neosuniversity.videolibrary.repository.ActorRepository;
import com.neosuniversity.videolibrary.repository.MovieRepository;
import com.neosuniversity.videolibrary.repository.TypeMovieRepository;
import com.neosuniversity.videolibrary.util.MovieUtil;
import com.neosuniversity.videolibrary.util.TypeMovieUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MovieTest {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private TypeMovieRepository typeMovieRepository;
	
	@Autowired
	private ActorRepository actorRepository;

	public void createMovieTest() {

		log.info("----------------------------------------------");
		log.info("SAVE MOVIE::::");

		Movie movie = MovieUtil.createMovieMockup();

		movieRepository.save(movie);
		log.info("----------------------------------------------");
	}

	public void createMovieAndTypeTest() {

		log.info("----------------------------------------------");
		log.info("SAVE MOVIE::::");

		Movie movie = MovieUtil.createMovieMockup();
		TypeMovie typemovie = TypeMovieUtil.createTypeMovieMockup();

		movie.setTypemovie(typemovie);
		movieRepository.save(movie);
		log.info("----------------------------------------------");
	}
	
	public void createMovieFullTest(Long IdTypeMovie) {

		log.info("----------------------------------------------");
		log.info("SAVE MOVIE::::");

		Movie movie = MovieUtil.createMovieMockup();
		Optional<TypeMovie> typeMovie= typeMovieRepository.findById(IdTypeMovie);
		Set<Actor> actors = new HashSet<Actor>(actorRepository.findAll()); 
		
		movie.setTypemovie(typeMovie.get());
		movie.setActors(actors);
		movieRepository.save(movie);
		
		log.info("----------------------------------------------");
	}

	public Movie readMovieTest(Long idMovie) {
		log.info("----------------------------------------------");
		log.info("READ MOVIE::::");

		Optional<Movie> movie = movieRepository.findById(idMovie);

		if (movie.isPresent()) {
			log.info(movie.toString());
			log.info("----------------------------------------------");
			return movie.get();
		} else {
			log.info("Not Found Movie: " + idMovie);
			log.info("----------------------------------------------");
			return null;
		}

	}
	public void readAllMoviesTest() {
		log.info("----------------------------------------------");
		log.info("READ ALL MOVIES::::");

		List<Movie> movies = movieRepository.findAll();

		if (Optional.ofNullable(movies).isPresent()) {
			movies.forEach(System.out::println); 
			log.info("----------------------------------------------");
			
		} else {
			log.info("Not Found Movies in DB");
			log.info("----------------------------------------------");
		}

	}
	public void readAllMoviesOrderByTest(String title) {
		log.info("----------------------------------------------");
		log.info("READ MOVIES BY TITLE::::");

		List<Movie> movies = movieRepository.findMovieByTitle(title);

		if (Optional.ofNullable(movies).isPresent()) {
			movies.forEach(System.out::println); 
			log.info("----------------------------------------------");
			
		} else {
			log.info("Not Found Movies in DB");
			log.info("----------------------------------------------");
		}

	}

	public void updateMovieTest(Long idMovie, Long idTypeMovie) {

		log.info("----------------------------------------------");
		log.info("UPDATE MOVIE::::");

		Movie readMovie = readMovieTest(idMovie);
		Movie updateMovie = MovieUtil.updateMovieMockup();
		Optional<TypeMovie> type = typeMovieRepository.findById(idTypeMovie);

		if (Optional.ofNullable(readMovie).isPresent()) {
			readMovie.setTitle(updateMovie.getTitle());
			readMovie.setYear(updateMovie.getYear());
			readMovie.setSynopsis(updateMovie.getSynopsis());
			if (Optional.ofNullable(type).isPresent()) {
				readMovie.setTypemovie(type.get());
				movieRepository.save(readMovie);
				log.info("----------------------------------------------");
				readMovieTest(idMovie);
			}
		} else {
			log.info("Not Update Movie: " + idMovie);
			log.info("----------------------------------------------");
		}

	}

	public void deleteMovieTest(Long idMovie) {

		log.info("----------------------------------------------");
		log.info("DELETE MOVIE::::");

		Movie movie = readMovieTest(idMovie);

		if (Optional.ofNullable(movie).isPresent()) {
			movieRepository.delete(movie);
			log.info("----------------------------------------------");
		} else {
			log.info("Not Delete Movie: " + idMovie);
			log.info("----------------------------------------------");
		}

	}

}
