package com.neosuniversity.videolibrary.util;

import com.neosuniversity.videolibrary.entities.Actor;

public interface ActorUtil {

	public static Actor createActorMockup() {
		Actor actor = new Actor();
//		actor.setName("Veronica");
//		actor.setLasname("Cartwright");
//		actor.setAge(78);
//		actor.setName("Tom");
//		actor.setLasname("Skerritt");
//		actor.setAge(67);
		actor.setName("Veronica");
		actor.setLasname("Cartwright");
		actor.setAge(78);
		
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
