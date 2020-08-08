package com.neosuniversity.videolibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.neosuniversity.videolibrary.test.MovieTest;




@SpringBootApplication
public class VideoLibraryApplication implements CommandLineRunner {

	@Autowired
	private MovieTest movieTest;
	
	public static void main(String[] args) {
		SpringApplication.run(VideoLibraryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		movieTest.createMovieTest();
		movieTest.readMovieTest(10L);
		movieTest.updateMovieTest(10L);
		movieTest.deleteMovieTest(10L);
		
	}

}
