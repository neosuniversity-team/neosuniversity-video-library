package com.neosuniversity.videolibrary.persistence;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neosuniversity.videolibrary.entities.Actor;
import com.neosuniversity.videolibrary.repository.ActorRepository;

@Repository
public class ActorPersistence {
	
	@Autowired
	private ActorRepository actorRepository;
	
	public void createActor(Actor actor) {
		actorRepository.save(actor);

	}

	public Optional<Actor> readActorById(Long idMovie) {
		return actorRepository.findById(idMovie);
	}

	public void updateActor(Actor actor) {
		actorRepository.save(actor);

	}

	public void deleteActor(Actor actor) {
		actorRepository.delete(actor);
	}

}
