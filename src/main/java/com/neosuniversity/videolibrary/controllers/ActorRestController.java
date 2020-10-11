
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

import com.neosuniversity.videolibrary.business.ActorBusinessI;
import com.neosuniversity.videolibrary.entities.Actor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/neosuniversity/actors")
public class ActorRestController {
	
	
	@Autowired
	private ActorBusinessI actorBusinessI;
	
	
	@PostMapping(consumes = "application/json",produces = "application/json")
	public ResponseEntity<Actor> createActor(@Valid @RequestBody Actor actor) {
		
		log.info(actor.toString());
		
		actorBusinessI.createActor(actor);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{idActor}")
                                    .buildAndExpand(actor.getIdActor())
                                    .toUri();
        return ResponseEntity.created(location).body(actor);
	}

	
	
	@GetMapping(path="/{idActor}", produces = "application/json")
	public ResponseEntity<Actor> readActorById(@PathVariable("idActor") Long idActor) {
		
		return ResponseEntity.ok()
				.header("idActor",String.valueOf(idActor))
				.body(actorBusinessI.readActor(idActor));
	}
	
	@PutMapping(consumes = "application/json",produces = "application/json")
	public ResponseEntity<Actor> updateActor(@Valid @RequestBody Actor actor) {
		
		log.info(actor.toString());
		
		actorBusinessI.updateActor(actor);
       
		return ResponseEntity.ok()
		.header("IdTypeMovie",String.valueOf(actor.getIdActor()))
		.body(actorBusinessI.readActor(actor.getIdActor()));
	}
	
	@DeleteMapping(path="/{idActor}",produces = "application/json")
	public ResponseEntity<?> deleteTypeMovie(@PathVariable("idActor") Long idActor) {
		
		actorBusinessI.deleteActor(idActor);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(idActor)
                .toUri();
       
		return ResponseEntity.ok().location(location).build();
	}
	
	@GetMapping( produces = "application/json")
	public ResponseEntity<List<Actor>> readActorAll() {
		
		return ResponseEntity.ok()
				.body(actorBusinessI.readActorAll());
	}

}
