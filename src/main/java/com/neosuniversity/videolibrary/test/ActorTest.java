package com.neosuniversity.videolibrary.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosuniversity.videolibrary.entities.Actor;
import com.neosuniversity.videolibrary.entities.Address;
import com.neosuniversity.videolibrary.repository.ActorRepository;
import com.neosuniversity.videolibrary.util.ActorUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ActorTest {

	@Autowired
	private ActorRepository actorRepository;

	public void createActorTest() {

		log.info("----------------------------------------------");
		log.info("SAVE ACTOR::::");

		Actor actor = ActorUtil.createActorMockup();

		actorRepository.save(actor);
		log.info("----------------------------------------------");
	}

	public void createActorAndAddressTest() {

		log.info("----------------------------------------------");
		log.info("SAVE ACTOR + ADDRESS::::");

		Actor actor = ActorUtil.createActorMockup();
		Address address1 = new Address();
		Address address2 = new Address();
		List<Address> addresses= new ArrayList<>();
		address1.setAddress("First Address");
		address2.setAddress("Second Address");
		addresses.add(address1);
		addresses.add(address2);
		actor.setAddresses(addresses);

		actorRepository.save(actor);
		log.info("----------------------------------------------");
	}
	
	public Actor readActorTest(Long idActor) {
		log.info("----------------------------------------------");
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

	public void updateActorTest(Long idActor) {

		log.info("----------------------------------------------");
		log.info("UPDATE ACTOR::::");

		Actor readActor = readActorTest(idActor);
		Actor updateActor = ActorUtil.updateActorMockup();

		if (Optional.ofNullable(readActor).isPresent()) {
			readActor.setName(updateActor.getName());
			readActor.setLasname(updateActor.getLasname());
			readActor.setAge(updateActor.getAge());
			actorRepository.save(readActor);
			log.info("----------------------------------------------");
			readActorTest(idActor);
		} else {
			log.info("Not Update Actor: " + idActor);
			log.info("----------------------------------------------");
		}

	}

	public void deleteActorTest(Long idActor) {

		log.info("----------------------------------------------");
		log.info("DELETE ACTOR::::");

		Actor actor = readActorTest(idActor);

		if (Optional.ofNullable(actor).isPresent()) {
			actorRepository.delete(actor);
			log.info("----------------------------------------------");
		} else {
			log.info("Not Delete Actor: " + idActor);
			log.info("----------------------------------------------");
		}

	}

}
