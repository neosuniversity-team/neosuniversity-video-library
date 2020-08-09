## Spring JPA Mapeo one to one

### En practica tiene por objetivo:
- Aprender como hacer una relacion one to one
- Utilizar las anotacion @OneToOne
- Aprender acerca fetch=FetchType.EAGER
- Aprender acerca cascade=CascadeType.PERSIST

### Crear la entidad TypeMovie
``` js
package com.neosuniversity.videolibrary.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name="TYPE_MOVIE",schema="VIDEODB")
public class TypeMovie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_TYPE_MOVIE",nullable=false)
	private Long idTypeMovie;
	
	@Column(name="TYPE_DESCRIPTION",length=100,nullable=false)
	private String type;

}
```
### Crear TypeMovieRepository
```
package com.neosuniversity.videolibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neosuniversity.videolibrary.entities.TypeMovie;

public interface TypeMovieRepository extends JpaRepository<TypeMovie, Long> {

}

```

### Crear TypeUtilMovie
```
package com.neosuniversity.videolibrary.util;


import com.neosuniversity.videolibrary.entities.TypeMovie;

public interface TypeMovieUtil {
	
	
	public static TypeMovie createTypeMovieMockup() {

		TypeMovie typeMovie = new TypeMovie();
		typeMovie.setType("Sci-Fi");
		
		return typeMovie;
	}

	public static TypeMovie updateTypeMovieMockup() {

		TypeMovie typeMovie = new TypeMovie();
		typeMovie.setType("Sci-Fi-II");
		
		return typeMovie;
	}

}
```
### Crear TypeMovieTest

```
package com.neosuniversity.videolibrary.test;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosuniversity.videolibrary.entities.TypeMovie;
import com.neosuniversity.videolibrary.repository.TypeMovieRepository;
import com.neosuniversity.videolibrary.util.TypeMovieUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TypeMovieTest {

	@Autowired
	private TypeMovieRepository typeMovieRepository;

	public void createTypeMovieTest() {

		log.info("----------------------------------------------");
		log.info("SAVE TYPE MOVIE::::");

		TypeMovie typeMovie = TypeMovieUtil.createTypeMovieMockup();

		typeMovieRepository.save(typeMovie);
		log.info("----------------------------------------------");
	}

	public TypeMovie readTypeMovieTest(Long idTypeMovie) {
		log.info("----------------------------------------------");
		log.info("READ TYPE MOVIE::::");

		Optional<TypeMovie> typeMovie = typeMovieRepository.findById(idTypeMovie);

		if (typeMovie.isPresent()) {
			log.info(typeMovie.toString());
			log.info("----------------------------------------------");
			return typeMovie.get();
		} else {
			log.info("Not Found typeMovie: " + idTypeMovie);
			log.info("----------------------------------------------");
			return null;
		}
	}

	public void updateTypeMovieTest(Long idTypeMovie,String type) {
		log.info("----------------------------------------------");
		log.info("UPDATE TYPE MOVIE::::");

		TypeMovie typeMovie = readTypeMovieTest(idTypeMovie);
		typeMovie.setType(type);

		if (Optional.ofNullable(typeMovie).isPresent()) {
			typeMovieRepository.save(typeMovie);
			log.info("----------------------------------------------");
			readTypeMovieTest(idTypeMovie);
		} else {
			log.info("Not Update typeMovie: " + idTypeMovie);
			log.info("----------------------------------------------");
		}
	}

	public void deleteTypeMovieTest(Long idTypeMovie) {
		log.info("----------------------------------------------");
		log.info("DELETE MOVIE::::");

		TypeMovie typeMovie = readTypeMovieTest(idTypeMovie);

		if (Optional.ofNullable(typeMovie).isPresent()) {
			typeMovieRepository.delete(typeMovie);
			log.info("----------------------------------------------");
		} else {
			log.info("Not Delete TypeMovie: " + idTypeMovie);
			log.info("----------------------------------------------");
		}
	}
}

```
### Realizar Testing TypeMovie (create)
```
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
		Long idMovie = 1L;
		Long idTypeMovie = 4L;
		
//		movieTest.createMovieTest();
//		movieTest.createMovieAndTypeTest();
		movieTest.readMovieTest(idMovie);
//		movieTest.updateMovieTest(idMovie,idTypeMovie);
//		movieTest.deleteMovieTest(idMovie);
		
		
//		Long idActor = 1L;
//		actorTest.createActorTest();
//		actorTest.readActorTest(idActor);
//		actorTest.updateActorTest(idActor);
//		actorTest.deleteActorTest(idActor);
		
		Long idTypeMovie = 1L;
		typeMovie.createTypeMovieTest();
//		typeMovie.readTypeMovieTest(idTypeMovie);
//		typeMovie.updateTypeMovieTest(idTypeMovie,"COMEDY");
//		typeMovie.deleteTypeMovieTest(idTypeMovie);
		
	}

}

```
### Realizar Testing TypeMovie (read, update y delete)
```
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
		Long idMovie = 1L;
		Long idTypeMovie = 4L;
		
//		movieTest.createMovieTest();
//		movieTest.createMovieAndTypeTest();
		movieTest.readMovieTest(idMovie);
//		movieTest.updateMovieTest(idMovie,idTypeMovie);
//		movieTest.deleteMovieTest(idMovie);
		
		
//		Long idActor = 1L;
//		actorTest.createActorTest();
//		actorTest.readActorTest(idActor);
//		actorTest.updateActorTest(idActor);
//		actorTest.deleteActorTest(idActor);
		
		Long idTypeMovie = 1L;
//		typeMovie.createTypeMovieTest();
		typeMovie.readTypeMovieTest(idTypeMovie);
		typeMovie.updateTypeMovieTest(idTypeMovie,"COMEDY");
		typeMovie.deleteTypeMovieTest(idTypeMovie);
		
	}

}

```
### Realizar Mapeo one to one
```
package com.neosuniversity.videolibrary.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@Table(name="MOVIE",schema="VIDEODB")
public class Movie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_MOVIE",nullable=false,unique=false)
	private Long idMovie;
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "ID_TYPE_MOVIE")
	private TypeMovie typemovie;
	
	
	@Column(name="TITLE",length=200,nullable=false)
	private String title;
	
	@Column(name="YEAR",nullable=false)
	private int year;
	
	@Column(name="SYNOPSIS",length=900,nullable=true)
	private String synopsis;
	
	@Column(name="DURATION",nullable=true)
	private String duration;

}
```
### Modificar MovieTest
```
package com.neosuniversity.videolibrary.test;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosuniversity.videolibrary.entities.Movie;
import com.neosuniversity.videolibrary.entities.TypeMovie;
import com.neosuniversity.videolibrary.repository.MovieRepository;
import com.neosuniversity.videolibrary.repository.TypeMovieRepository;
import com.neosuniversity.videolibrary.util.MovieUtil;
import com.neosuniversity.videolibrary.util.TypeMovieUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MovieTest {

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private TypeMovieRepository typeMovieRepository;

	public void createMovieTest() {

		log.info("----------------------------------------------");
		log.info("SAVE MOVIE::::");

		Movie movie = MovieUtil.createMovieMockup();

		movieRepository.save(movie);
		log.info("----------------------------------------------");
	}

	public void createMovieAndTypeTest() {

		log.info("----------------------------------------------");
		log.info("SAVE MOVIE::::");

		Movie movie = MovieUtil.createMovieMockup();
		TypeMovie typemovie = TypeMovieUtil.createTypeMovieMockup();

		movie.setTypemovie(typemovie);
		movieRepository.save(movie);
		log.info("----------------------------------------------");
	}

	public Movie readMovieTest(Long idMovie) {
		log.info("----------------------------------------------");
		log.info("READ MOVIE::::");

		Optional<Movie> movie = movieRepository.findById(idMovie);

		if (movie.isPresent()) {
			log.info(movie.toString());
			log.info("----------------------------------------------");
			return movie.get();
		} else {
			log.info("Not Found Movie: " + idMovie);
			log.info("----------------------------------------------");
			return null;
		}

	}

	public void updateMovieTest(Long idMovie, Long idTypeMovie) {

		log.info("----------------------------------------------");
		log.info("UPDATE MOVIE::::");

		Movie readMovie = readMovieTest(idMovie);
		Movie updateMovie = MovieUtil.updateMovieMockup();
		Optional<TypeMovie> type = typeMovieRepository.findById(idTypeMovie);

		if (Optional.ofNullable(readMovie).isPresent()) {
			readMovie.setTitle(updateMovie.getTitle());
			readMovie.setYear(updateMovie.getYear());
			readMovie.setSynopsis(updateMovie.getSynopsis());
			if (Optional.ofNullable(type).isPresent()) {
				readMovie.setTypemovie(type.get());
				movieRepository.save(readMovie);
				log.info("----------------------------------------------");
				readMovieTest(idMovie);
			}
		} else {
			log.info("Not Update Movie: " + idMovie);
			log.info("----------------------------------------------");
		}

	}

	public void deleteMovieTest(Long idMovie) {

		log.info("----------------------------------------------");
		log.info("DELETE MOVIE::::");

		Movie movie = readMovieTest(idMovie);

		if (Optional.ofNullable(movie).isPresent()) {
			movieRepository.delete(movie);
			log.info("----------------------------------------------");
		} else {
			log.info("Not Delete Movie: " + idMovie);
			log.info("----------------------------------------------");
		}

	}

}
```
### Realizar Testing Mapeo One to One (Movie read)

```
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
		Long idMovie = 1L;
		Long idTypeMovie = 4L;
		
//		movieTest.createMovieTest();
//		movieTest.createMovieAndTypeTest();
		movieTest.readMovieTest(idMovie);
//		movieTest.updateMovieTest(idMovie,idTypeMovie);
//		movieTest.deleteMovieTest(idMovie);
		
		
//		Long idActor = 1L;
//		actorTest.createActorTest();
//		actorTest.readActorTest(idActor);
//		actorTest.updateActorTest(idActor);
//		actorTest.deleteActorTest(idActor);
		
//		Long idTypeMovie = 6L;
//		typeMovie.createTypeMovieTest();
//		typeMovie.readTypeMovieTest(idTypeMovie);
//		typeMovie.updateTypeMovieTest(idTypeMovie,"COMEDY");
//		typeMovie.deleteTypeMovieTest(idTypeMovie);
		
		
	}

}

```
### Realizar Mapeo one to one (cascade=CascadeType.PERSIST)
```
package com.neosuniversity.videolibrary.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@Table(name="MOVIE",schema="VIDEODB")
public class Movie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_MOVIE",nullable=false,unique=false)
	private Long idMovie;
	
	@OneToOne(cascade=CascadeType.PERSIST,fetch=FetchType.EAGER)
	@JoinColumn(name = "ID_TYPE_MOVIE")
	private TypeMovie typemovie;
	
	
	@Column(name="TITLE",length=200,nullable=false)
	private String title;
	
	@Column(name="YEAR",nullable=false)
	private int year;
	
	@Column(name="SYNOPSIS",length=900,nullable=true)
	private String synopsis;
	
	@Column(name="DURATION",nullable=true)
	private String duration;

}
```
### Realizar Testing Mapeo One to One (Movie create, read)

```
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
		Long idMovie = 1L;
		Long idTypeMovie = 4L;
		
//		movieTest.createMovieTest();
		movieTest.createMovieAndTypeTest();
		movieTest.readMovieTest(idMovie);
//		movieTest.updateMovieTest(idMovie,idTypeMovie);
//		movieTest.deleteMovieTest(idMovie);
		
		
//		Long idActor = 1L;
//		actorTest.createActorTest();
//		actorTest.readActorTest(idActor);
//		actorTest.updateActorTest(idActor);
//		actorTest.deleteActorTest(idActor);
		
//		Long idTypeMovie = 6L;
//		typeMovie.createTypeMovieTest();
//		typeMovie.readTypeMovieTest(idTypeMovie);
//		typeMovie.updateTypeMovieTest(idTypeMovie,"COMEDY");
//		typeMovie.deleteTypeMovieTest(idTypeMovie);
		
		
	}

}

```
### Realizar Testing Mapeo One to One (read, update, delete)

```
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
		Long idMovie = 1L;
		Long idTypeMovie = 4L;
		
//		movieTest.createMovieTest();
		movieTest.createMovieAndTypeTest();
		movieTest.readMovieTest(idMovie);
		movieTest.updateMovieTest(idMovie,idTypeMovie);
		movieTest.deleteMovieTest(idMovie);
		
		
//		Long idActor = 1L;
//		actorTest.createActorTest();
//		actorTest.readActorTest(idActor);
//		actorTest.updateActorTest(idActor);
//		actorTest.deleteActorTest(idActor);
		
//		Long idTypeMovie = 6L;
//		typeMovie.createTypeMovieTest();
//		typeMovie.readTypeMovieTest(idTypeMovie);
//		typeMovie.updateTypeMovieTest(idTypeMovie,"COMEDY");
//		typeMovie.deleteTypeMovieTest(idTypeMovie);
		
		
	}

}

```