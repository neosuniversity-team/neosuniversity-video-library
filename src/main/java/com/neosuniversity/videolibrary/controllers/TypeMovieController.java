package com.neosuniversity.videolibrary.controllers;

import java.net.URI;
import java.util.List;

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

import com.neosuniversity.videolibrary.business.TypeMovieBusinessI;
import com.neosuniversity.videolibrary.entities.TypeMovie;
import javax.validation.Valid;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/neosuniversity/typeMovies")
public class TypeMovieController {
	
	@Autowired
	private TypeMovieBusinessI typeMovieBusinessI;
	
	
	@PostMapping(consumes = "application/json",produces = "application/json")
	public ResponseEntity<TypeMovie> createTypeMovie(@Valid @RequestBody TypeMovie typeMovie) {
		
		log.info(typeMovie.toString());
		
		typeMovieBusinessI.createTypeMovie(typeMovie);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{idTypeMovie}")
                                    .buildAndExpand(typeMovie.getIdTypeMovie())
                                    .toUri();
        return ResponseEntity.created(location).body(typeMovie);
	}

	
	
	@GetMapping(path="/{idTypeMovie}", produces = "application/json")
	public ResponseEntity<TypeMovie> readTypeMovieById(@PathVariable("idTypeMovie") Long idTypeMovie) {
		
		return ResponseEntity.ok()
				.header("idCountry",String.valueOf(idTypeMovie))
				.body(typeMovieBusinessI.readTypeMovie(idTypeMovie));
	}
	
	@PutMapping(consumes = "application/json",produces = "application/json")
	public ResponseEntity<TypeMovie> updateTypeMovie(@Valid @RequestBody TypeMovie typeMovie) {
		
		log.info(typeMovie.toString());
		
		typeMovieBusinessI.updateTypeMovie(typeMovie);
       
		return ResponseEntity.ok()
		.header("IdTypeMovie",String.valueOf(typeMovie.getIdTypeMovie()))
		.body(typeMovieBusinessI.readTypeMovie(typeMovie.getIdTypeMovie()));
	}
	
	@DeleteMapping(path="/{idTypeMovie}",produces = "application/json")
	public ResponseEntity<?> deleteTypeMovie(@PathVariable("idTypeMovie") Long idTypeMovie) {
		
		typeMovieBusinessI.deleteTypeMovieTest(idTypeMovie);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(idTypeMovie)
                .toUri();
       
		return ResponseEntity.ok().location(location).build();
	}
	
	@GetMapping( produces = "application/json")
	public ResponseEntity<List<TypeMovie>> readTypeMovieAll() {
		
		return ResponseEntity.ok()
				.body(typeMovieBusinessI.readTypeMovieAll());
	}
	

}
