/**
 * 
 */
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
}
