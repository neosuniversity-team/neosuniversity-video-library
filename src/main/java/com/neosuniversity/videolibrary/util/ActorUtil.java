  
package com.neosuniversity.videolibrary.util;

import com.neosuniversity.videolibrary.entities.Actor;

public interface ActorUtil {

	public static Actor createActorMockup() {
		Actor actor = new Actor();
		actor.setName("Sigourney");
		actor.setLasname("Weaver");
		actor.setAge(71);
		
		return actor;
	}

	public static Actor updateActorMockup() {
		Actor actor = new Actor();
		actor.setName("Sigourneyyy");
		actor.setLasname("Weaverrr");
		actor.setAge(70);
		return actor;
	}

}