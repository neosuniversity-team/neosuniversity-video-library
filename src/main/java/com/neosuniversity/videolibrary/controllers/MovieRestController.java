package com.neosuniversity.videolibrary.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.neosuniversity.videolibrary.business.MovieBusinessI;
import com.neosuniversity.videolibrary.entities.Movie;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/neosuniversity/movies")
public class MovieRestController {
	
	@Autowired
	private MovieBusinessI movieBusinessI;
	
	@PostMapping(consumes = "application/json",produces = "application/json")
	public ResponseEntity<Movie> createMovie(@Valid @RequestBody Movie movie) {
		
		log.info(movie.toString());
		
		movieBusinessI.createMovie(movie);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{idActor}")
                                    .buildAndExpand(movie.getIdMovie())
                                    .toUri();
        return ResponseEntity.created(location).body(movie);
	}
	
	@GetMapping(path="/{idMovie}", produces = "application/json")
	public ResponseEntity<Movie> readMovieById(@PathVariable("idMovie") Long idMovie) {
		
		return ResponseEntity.ok()
				.header("idMovie",String.valueOf(idMovie))
				.body(movieBusinessI.readMovie(idMovie));
	}
	
	@GetMapping( produces = "application/json")
	public ResponseEntity<List<Movie>> readMovieAll() {
		
		return ResponseEntity.ok()
				.body(movieBusinessI.readMovieAll());
	}
	

	@PutMapping(consumes = "application/json",produces = "application/json")
	public ResponseEntity<Movie> updateMovie(@Valid @RequestBody Movie movie) {
		
		log.info(movie.toString());
		
		movieBusinessI.updateMovie(movie);
       
		return ResponseEntity.ok()
		.header("IdMovie",String.valueOf(movie.getIdMovie()))
		.body(movieBusinessI.readMovie(movie.getIdMovie()));
	}
	
	@DeleteMapping(path="/{idMovie}",produces = "application/json")
	public ResponseEntity<?> deleteMovie(@PathVariable("idMovie") Long idMovie) {
		
		movieBusinessI.deleteMovie(idMovie);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(idMovie)
                .toUri();
       
		return ResponseEntity.ok().location(location).build();
	}

}
