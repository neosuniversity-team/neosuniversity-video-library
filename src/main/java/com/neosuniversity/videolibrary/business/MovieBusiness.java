package com.neosuniversity.videolibrary.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosuniversity.videolibrary.entities.Movie;
import com.neosuniversity.videolibrary.repository.MovieRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MovieBusiness  implements MovieBusinessI {

	@Autowired
	private MovieRepository movieRepository;
	
	
	@Override
	public void createMovie(Movie movie) {
		log.info("----------------------------------------------");
		log.info("SAVE MOVIE::::");

		movieRepository.save(movie);
		log.info("----------------------------------------------");

	}

	
	@Override
	public Movie readMovie(Long idMovie) {
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

	
	@Override
	public void updateMovie(Movie movie) {
		log.info("----------------------------------------------");
		log.info("UPDATE ACTOR::::");
		movieRepository.findById(movie.getIdMovie()).map(movieDB -> {
			validateMovieData(movie, movieDB);
			return movieRepository.save(movieDB);
		});
	}

	
	@Override
	public void deleteMovie(Long idMovie) {
		log.info("----------------------------------------------");
		log.info("DELETE MOVIE::::");

		Movie movie = readMovie(idMovie);

		if (Optional.ofNullable(movie).isPresent()) {
			movieRepository.delete(movie);
			log.info("----------------------------------------------");
		} else {
			log.info("Not Delete Movie: " + idMovie);
			log.info("----------------------------------------------");
		}

	}

	
	@Override
	public List<Movie> readMovieAll() {
		log.info("----------------------------------------------");
		log.info("GET ALL Movies::::");
		return movieRepository.findAll();
	}

}
