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
