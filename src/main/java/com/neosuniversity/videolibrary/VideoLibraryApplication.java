package com.neosuniversity.videolibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.neosuniversity.videolibrary.test.ActorTest;
import com.neosuniversity.videolibrary.test.MovieTest;


@SpringBootApplication
public class VideoLibraryApplication implements CommandLineRunner {

	@Autowired
	private MovieTest movieTest;
	@Autowired
	private ActorTest actorTest;
	
	
	public static void main(String[] args) {
		SpringApplication.run(VideoLibraryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		Long idMovie = 1L;
		
//		movieTest.createMovieTest();
//		movieTest.readMovieTest(idMovie);
//		movieTest.updateMovieTest(idMovie);
//		movieTest.deleteMovieTest(idMovie);
		
		
		Long idActor = 1L;
		actorTest.createActorTest();
//		actorTest.readActorTest(idActor);
//		actorTest.updateActorTest(idActor);
//		actorTest.deleteActorTest(idActor);
	}

}