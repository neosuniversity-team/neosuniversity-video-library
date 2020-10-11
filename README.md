## Spring Rest entidad Actor

En esta práctica aplicará los conocimientos aprendidos acerca de servicios Rest utilizando peticiones:
* GET
* POST
* PUT
* DELETE

Tambien conceptos de validación y el manejo de ResponseEntity para las Response de nuestros servicios Rest

## Nota: Como prerequisito es necesario borrar todo lo que tengamos en nuestro esquema videodb y ejecutar nuestro script populate.sql

### Listado de archivos:
### Agregar dependencias en el archivo pom.xml
``` js
<dependencies>
.......
.......
<!--  ExceptionUtils.validation -->
		<dependency>
			<groupId>org.apache.commons</groupId>
			<artifactId>commons-lang3</artifactId>
		</dependency>
.......
.......
</dependencies>
```
### Crear la interface ActorBusinessI con los siguientes metodos
``` js
package com.neosuniversity.videolibrary.business;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.neosuniversity.videolibrary.entities.Actor;
import com.neosuniversity.videolibrary.entities.Address;

public interface ActorBusinessI {

	void createActor(Actor actor);
	
	Actor readActor(Long idActor);

	void updateActor(Actor actor);

	void deleteActor(Long idActor);

	List<Actor> readActorAll();
}
```
### Crear la implementacion ActorBusiness de la interface ActorBusinessI
``` js
package com.neosuniversity.videolibrary.business;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosuniversity.videolibrary.entities.Actor;
import com.neosuniversity.videolibrary.repository.ActorRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ActorBusiness implements ActorBusinessI {

	@Autowired
	private ActorRepository actorRepository;
	
	@Override
	public void createActor(Actor actor) {
		log.info("----------------------------------------------");
		log.info("SAVE ACTOR::::");
		actorRepository.save(actor);
	}
	
	@Override
	public Actor readActor(Long idActor) {
		log.info("READ ACTOR::::");

		Optional<Actor> actor = actorRepository.findById(idActor);

		if (actor.isPresent()) {
			log.info(actor.toString());
			log.info("----------------------------------------------");
			return actor.get();
		} else {
			log.info("Not Found Actor: " + idActor);
			log.info("----------------------------------------------");
			return null;
		}
	}

	@Override
	public void updateActor(Actor actor) {
		log.info("----------------------------------------------");
		log.info("UPDATE ACTOR::::");
		actorRepository.findById(actor.getIdActor()).map(actorDB -> {
			validateActorData(actor, actorDB);
			return actorRepository.save(actorDB);
		});
		
		
	}

	@Override
	public void deleteActor(Long idActor) {
	
		log.info("----------------------------------------------");
		log.info("DELETE ACTOR::::");

		Actor actor = readActor(idActor);

		if (Optional.ofNullable(actor).isPresent()) {
			actorRepository.delete(actor);
			log.info("----------------------------------------------");
		} else {
			log.info("Not Delete Actor: " + idActor);
			log.info("----------------------------------------------");
		}
	}

	@Override
	public List<Actor> readActorAll() {
		log.info("----------------------------------------------");
		log.info("GET ALL ACTORS::::");
		return actorRepository.findAll();
	}
}
```
### Para poder hacer el update de la informacion de un actor es necesario realizar algunas validaciones, para lo cual vamos a crear unos metodos adicionales en la interface ActorBusinessI
``` js
......
......
default void validateActorData(Actor actor, Actor actorDB) {
		
		if (!validateString(actor.getName())) {
			actorDB.setName(actor.getName());
		}
		if (!validateString(actor.getLasname())) {
			actorDB.setLasname(actor.getLasname());
		}
		if (!validateInteger(actor.getAge())) {
			actorDB.setAge(actor.getAge());
		}
		if (!validateListAddress(actor.getAddresses())) {
			actorDB.setAddresses(actor.getAddresses());
		}
	}
	
	default  boolean validateString(String value) {
		value = StringUtils.defaultString(value);
		if (StringUtils.isEmpty(value) && StringUtils.isBlank(value)) {
			return true;
		} else {
			return false;
		}

	}
	default  boolean validateInteger(int value) {
		if(value==0) {
			return true;
		}else {
			return false;
		}
	}
	default  boolean validateListAddress(List<Address> addresses) {
		if (Optional.ofNullable(addresses).isPresent()) {
			return false;
		}else {
			return true;
		}
	}
........
........
```
### Por ultimo vamos a crear los servicios Rest para la entidad Actor - ActorRestController
``` js
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
```