package com.neosuniversity.videolibrary.components;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import com.neosuniversity.videolibrary.entities.Movie;
import com.neosuniversity.videolibrary.services.MovieService;
import com.neosuniversity.videolibrary.util.MovieUtil;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class StartupCrud  implements CommandLineRunner , MovieUtil {
	
	@Autowired
	private MovieService movieService;


	@Override
	public void run(String... args) throws Exception {
		
	     saveMovie();
	     
	     //optional you can update or delete movies
	     
	    //  updateMovieById(14L);
	     //deleteMovieById(2L);
		
	}
	
	public void saveMovie() {
		
		log.info("Executing CRUD operation by Movie entity.....");
	
		log.info("----------------------------------------------");
		log.debug("SAVE MOVIE::::");
		
		Movie movie = MovieUtil.buildMovieMockup();
		movieService.save(movie);
		
		log.debug("Pelicula agregada:" + movie);
		
		
	}
	
	public void updateMovieById( Long idMovie) {
		
		log.info("----------------------------------------------");
		log.debug("READ MOVIE::::");
		
		
		Optional<Movie> optionalMovie = movieService.findById(idMovie);
		if(optionalMovie.isPresent()){
			log.debug("MOVIE: "+ optionalMovie.get().toString());
			
			
			log.info("----------------------------------------------");
			log.debug("UPDATE MOVIE::::");
			
			Movie updateMovie = new Movie();
			updateMovie = optionalMovie.get();
			updateMovie.setTitle("Alien");
			
			movieService.save(updateMovie);
			log.debug("Pelicula modificada:" + updateMovie);
		} else {
			log.error("No existe la pelicula con ID : " + idMovie);
		}
		
	}
	
	public void deleteMovieById(Long idMovie) {

		log.info("----------------------------------------------");
		log.debug("READ MOVIE::::");
		
	

		Optional<Movie> optionalMovieUpdate = movieService.findById(idMovie);
		if(optionalMovieUpdate.isPresent()){
			log.debug("MOVIE: "+ optionalMovieUpdate.get().toString());
			log.info("----------------------------------------------");
			log.debug("DELETE  MOVIE::::");
			
			movieService.delete(optionalMovieUpdate.get());
		} else {
			log.error("No existe la pelicula con ID : " + idMovie);
		}
		
	}

}
