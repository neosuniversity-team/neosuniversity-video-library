## Spring JPA Mapeo entidad Movie

En esta práctica aplicará los conceptos de Spring JPA para poder mapear la entidad Movie asi como lo son el uso de las anotaciones

* @Entity
* @Table
* @Id
* @GeneratedValue
* @GeneratedValue
* @Column

### Como requerimientos previos se necesita:
- Instalar BD Mysql 8.x --> https://dev.mysql.com/downloads/mysql/
- Crear la BD(Esquema) videodb
- Crear usuario :neosvideo	(Proporcionarle privilegios para usar el esquema videodb)
- Crear password:neosvideo
- Se recomienda instalar Mysqlworkbeanch o bien otro cliente para Mysql --> https://dev.mysql.com/downloads/workbench/



### Listado de archivos:
### Agregar dependencias en el archivo pom.xml
```
<dependencies>
.......
.......
<!-- add features by persistence DAO layer -->
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
<!-- add connection with mysql DB -->
	<dependency>
		<groupId>mysql</groupId>
		<artifactId>mysql-connector-java</artifactId>
		<scope>runtime</scope>
	</dependency>
.......
.......
</dependencies>
```

### Agregar la siguiente configuracion en application.properties
```
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/videodb?serverTimezone=UTC
spring.datasource.username=neosvideo
spring.datasource.password=neosvideo

spring.jpa.show-sql=true
logging.level.com.neosuniversity=DEBUG
```
### Crear la entidad Movie
```
package com.neosuniversity.videolibrary.entities;


import java.util.Date;

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
	
	@Column(name="TITLE",length=200,nullable=false)
	private String title;
	
	@Column(name="YEAR",nullable=false)
	private int year;
	
	@Column(name="SYNOPSIS",length=900,nullable=true)
	private String synopsis;
	
	@Column(name="DURATION",nullable=true)
	private Date duration;
	
	@Column(name="IMAGE_PATH",length=500,nullable=true)
	private String imagepath;
}

```
### Crear el repositorio MovieRepository
```
  
package com.neosuniversity.videolibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.neosuniversity.videolibrary.entities.Movie;


public interface MovieRepository extends JpaRepository <Movie, Long> {
	
}

```
### Crear una utileria MovieUtil
```
package com.neosuniversity.videolibrary.util;

import java.util.Date;

import com.neosuniversity.videolibrary.entities.Movie;

public interface MovieUtil {

	public static Movie createMovieMockup() {

		Movie movie = new Movie();
		movie.setTitle("Alien, the 8° passenger");
		movie.setYear(1979);
		movie.setSynopsis("After a space merchant vessel receives an unknown "
				+ "transmission as a distress call, one of the crew is attacked "
				+ "by a mysterious life form and they soon realize that its life " 
				+ "cycle has merely begun.");
		movie.setDuration(new Date());

		return movie;
	}

	public static Movie updateMovieMockup() {

		Movie movie = new Movie();
		movie.setTitle("Alien");
		movie.setYear(1978);
		movie.setSynopsis("After a space merchant vessel receives an unknown "
				+ "transmission as a distress call");
		movie.setDuration(new Date());

		return movie;
	}
}

```

### Crear servicio persistencia MoviePersistence
```
package com.neosuniversity.videolibrary.persistence;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neosuniversity.videolibrary.entities.Movie;
import com.neosuniversity.videolibrary.repository.MovieRepository;

@Repository
public class MoviePersistence {

	@Autowired
	private MovieRepository movieRepository;

	public void createMovie(Movie movie) {
		movieRepository.save(movie);

	}

	public Optional<Movie> readMovieById(Long idMovie) {

		return movieRepository.findById(idMovie);

	}

	public void updateMovie(Movie movie) {

		movieRepository.save(movie);

	}

	public void deleteMovie(Movie movie) {

		movieRepository.delete(movie);

	}

}
```
### Crear servicio MovieTest para realizar el testing de nuestra entidad Movie
```
package com.neosuniversity.videolibrary.test;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosuniversity.videolibrary.entities.Movie;
import com.neosuniversity.videolibrary.persistence.MoviePersistence;
import com.neosuniversity.videolibrary.util.MovieUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MovieTest {

	@Autowired
	private MoviePersistence moviePersistence;

	public void createMovieTest() {

		log.info("----------------------------------------------");
		log.info("SAVE MOVIE::::");

		Movie movie = MovieUtil.createMovieMockup();

		moviePersistence.createMovie(movie);
		log.info("----------------------------------------------");
	}

	public Movie readMovieTest(Long idMovie) {
		log.info("----------------------------------------------");
		log.info("READ MOVIE::::");

		Optional<Movie> movie = moviePersistence.readMovieById(idMovie);
		
		if(movie.isPresent()) {
			log.info(movie.toString());
			log.info("----------------------------------------------");
			return movie.get();
		}else {
			log.info("Not Found Movie: "+idMovie);
			log.info("----------------------------------------------");
			return null;
		}
		
		
	}

	public void updateMovieTest(Long idMovie) {

		log.info("----------------------------------------------");
		log.info("UPDATE MOVIE::::");
		
		Movie readMovie = readMovieTest(idMovie);
		Movie updateMovie = MovieUtil.updateMovieMockup();

		if(Optional.ofNullable(readMovie).isPresent()) {
			readMovie.setTitle(updateMovie.getTitle());
			readMovie.setYear(updateMovie.getYear());
			readMovie.setSynopsis(updateMovie.getSynopsis());
			moviePersistence.createMovie(readMovie);
			log.info("----------------------------------------------");
			readMovieTest(idMovie);
		}else {
			log.info("Not Update Movie: "+idMovie);
			log.info("----------------------------------------------");
		}

	}
	public void deleteMovieTest(Long idMovie) {

		log.info("----------------------------------------------");
		log.info("DELETE MOVIE::::");
		
		Movie movie = readMovieTest(idMovie);

		if(Optional.ofNullable(movie).isPresent()) {
			moviePersistence.deleteMovie(movie);
			log.info("----------------------------------------------");
		}else {
			log.info("Not Delete Movie: "+idMovie);
			log.info("----------------------------------------------");
		}

	}

}

```
### Modificar VideoLibraryApplication para poder ejecutar el testing de Movie (Read,Update and Delete)
```

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
		//Long idMovie = 1L;
		
		movieTest.createMovieTest();
//		movieTest.readMovieTest(idMovie);
//		movieTest.updateMovieTest(idMovie);
//		movieTest.deleteMovieTest(idMovie);
		
	}

}


```

### Modificar VideoLibraryApplication para poder ejecutar el testing de Movie (Create)
```

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
		Long idMovie = 1L;
		
//		movieTest.createMovieTest();
		movieTest.readMovieTest(idMovie);
		movieTest.updateMovieTest(idMovie);
		movieTest.deleteMovieTest(idMovie);
		
	}

}


```
