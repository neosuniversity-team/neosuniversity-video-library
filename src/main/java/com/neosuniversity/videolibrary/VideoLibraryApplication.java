package com.neosuniversity.videolibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.neosuniversity.videolibrary.test.ActorTest;
import com.neosuniversity.videolibrary.test.MovieTest;
import com.neosuniversity.videolibrary.test.TypeMovieTest;


@SpringBootApplication
public class VideoLibraryApplication implements CommandLineRunner {

	@Autowired
	private MovieTest movieTest;
	
	@Autowired
	private ActorTest actorTest;
	
	@Autowired
	private TypeMovieTest typeMovie;
	
	
	public static void main(String[] args) {
		SpringApplication.run(VideoLibraryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Long idMovie = 1L; //dependera que ID exista en la BD
		Long idTypeMovie = 1L; //dependera que ID exista en la BD
		Long idActor = 1L;    //dependera que ID exista en la BD
//		movieTest.createMovieTest();
//		movieTest.createMovieAndTypeTest();
//		movieTest.readMovieTest(idMovie);
//		movieTest.updateMovieTest(idMovie,idTypeMovie);
//		movieTest.deleteMovieTest(idMovie);
		
		
//		actorTest.createActorTest();
//		actorTest.readActorTest(idActor);
//		actorTest.updateActorTest(idActor);
//		actorTest.deleteActorTest(idActor);
		
//		typeMovie.createTypeMovieTest();
//		typeMovie.readTypeMovieTest(idTypeMovie);
//		typeMovie.updateTypeMovieTest(idTypeMovie,"COMEDY");
//		typeMovie.deleteTypeMovieTest(idTypeMovie);
		
		
	}

}
