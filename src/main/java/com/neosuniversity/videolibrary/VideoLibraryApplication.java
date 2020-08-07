package com.neosuniversity.videolibrary;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.neosuniversity.videolibrary.entities.Movie;
import com.neosuniversity.videolibrary.repository.MovieRepository;
import com.neosuniversity.videolibrary.util.MovieUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class VideoLibraryApplication implements CommandLineRunner, MovieUtil {

	@Autowired
	private MovieRepository movieRepository;

	public static void main(String[] args) {
		SpringApplication.run(VideoLibraryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Executing CRUD operation by Movie entity.....");
	
		log.info("----------------------------------------------");
		log.debug("SAVE MOVIE::::");
		
		Movie movie = MovieUtil.buildMovieMockup();
		movieRepository.save(movie);
		
		log.info("----------------------------------------------");
		log.debug("READ MOVIE::::");
		
		Optional<Movie> optionalMovie = movieRepository.findById(3L);
		if(optionalMovie.isPresent()){
			log.debug("MOVIE: "+ optionalMovie.get().toString());
		}
		
		log.info("----------------------------------------------");
		log.debug("UPDATE MOVIE::::");
		
		Movie updateMovie = new Movie();
		updateMovie = optionalMovie.get();
		updateMovie.setTitle("Alien");
		
		movieRepository.save(updateMovie);
		
		Optional<Movie> optionalMovieUpdate = movieRepository.findById(3L);
		if(optionalMovieUpdate.isPresent()){
			log.debug("MOVIE: "+ optionalMovieUpdate.get().toString());
		}
		log.info("----------------------------------------------");
		log.debug("DELETE  MOVIE::::");
		
		movieRepository.delete(optionalMovieUpdate.get());
		
	}

}
