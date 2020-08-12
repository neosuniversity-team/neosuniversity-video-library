## Spring JPA Mapeo many to many


## Modificar la entidad Movie (agregar la siguiente lineas)
``` js
	@ManyToMany(cascade=CascadeType.DETACH,fetch=FetchType.EAGER)
	@JoinTable(name="MOVIE_ACTOR",joinColumns=@JoinColumn(name="ID_MOVIE"),
	inverseJoinColumns=@JoinColumn(name="ID_ACTOR"))
	private Set<Actor> actors = new HashSet<>();
```
## Crear Actores utilizando VideolibraryApplication

``` js
createActorAndAddressTest
```

### Utilizando la siguiente informacion, agregando o modificando el Mockup de Actor
``` js
public static Actor createActorMockup() {
		Actor actor = new Actor();
		actor.setName("Sigourney");
		actor.setLasname("Weaver");
		actor.setAge(71);
		
		return actor;
	}
............................
public static Actor createActorMockup() {
		Actor actor = new Actor();
		actor.setName("Tom");
		actor.setLasname("Skerritt");
		actor.setAge(67);
		
		return actor;
	}
........
............................
public static Actor createActorMockup() {
		Actor actor = new Actor();
		actor.setName("Veronica");
		actor.setLasname("Cartwright");
		actor.setAge(78);
		
		return actor;
	}
........
```
### Para modificar las direcciones es necesario cambiar en cada ejecucion las siguientes lineas en 
``` js
public void createActorAndAddressTest()
........................................
address1.setAddress("First Address");
address2.setAddress("Second Address");
........................................
address1.setAddress("Primera Address");
address2.setAddress("Segunda Address");
........................................
address1.setAddress("MY Address");
address2.setAddress("Your Addres");
........................................
```

### Informacion en la BD para Actor + Address
``` js
select a.id_actor,a.name,b.id_address,b.address from actor as a,address_actor as b where a.id_actor=b.id_actor;
------------
'1','Veronica','1','First Address'
'1','Veronica','2','Second Address'
'2','Tom','3','Primera Address'
'2','Tom','4','Segunda Address'
'3','Veronica','5','MY Address'
'3','Veronica','6','Your Addres'

```
### Informacion en la BD para TypeMovie
``` js
public static TypeMovie createTypeMovieMockup() {

	TypeMovie typeMovie = new TypeMovie();
	typeMovie.setType("Sci-Fi");
//	ypeMovie.setType("Horror");
//	typeMovie.setType("Action");
		
	return typeMovie;
}
```
### Dar de alta 3 tipos de peliculas utilizando
``` js
typeMovie.createTypeMovieTest();
```
### Informacion en la BD para TypeMovie
``` js
'1','Sci-Fi'
'2','Horror'
'3','Action'
```
### Informacion para Movie + TypeMovie + Actor
``` js
movieTest.createMovieFullTest(idTypeMovie);
----------
public void createMovieFullTest(Long IdTypeMovie) {

		log.info("----------------------------------------------");
		log.info("SAVE MOVIE::::");

		Movie movie = MovieUtil.createMovieMockup();
		Optional<TypeMovie> typeMovie= typeMovieRepository.findById(IdTypeMovie);
		List<Actor> actors= actorRepository.findAll();
		movie.setTypemovie(typeMovie.get());
		movie.setActors(actors);
		movieRepository.save(movie);
		
		log.info("----------------------------------------------");
}
```
### Ahora consultar la informacion de la Movie utilizando:
``` js
movieTest.readMovieTest(idMovie);
```
### Ahora vamos a borrar la BD (esquema) y despues hay que ejecutar el proyecto comentando todas las lineas de:
``` js
VideoLibraryApplication.java
```
### El paso anterior nos va a crear la BD y las tablas. Ahora ejecutemos el script populate.sql que esta en src/main/resources utilizando workbeanch u otra tool
``` js
insert into type_movie(id_type_movie,type_description) VALUES(1,'Sci-Fi');
insert into type_movie(id_type_movie,type_description) VALUES(2,'Action');
insert into type_movie(id_type_movie,type_description) VALUES(3,'Horror');
insert into type_movie(id_type_movie,type_description) VALUES(4,'Drama');
insert into type_movie(id_type_movie,type_description) VALUES(5,'Comedy');
insert into type_movie(id_type_movie,type_description) VALUES(6,'Mystery');
insert into type_movie(id_type_movie,type_description) VALUES(7,'Crime');
insert into type_movie(id_type_movie,type_description) VALUES(8,'Western');
insert into type_movie(id_type_movie,type_description) VALUES(9,'Adventure,');
insert into type_movie(id_type_movie,type_description) VALUES(10,'Romance,');

insert into movie (id_movie,duration,synopsis,title,year,id_type_movie) VALUES(1,'1:57','After a space merchant vessel receives an unknown transmission as a distress call, one of the crew is attacked by a mysterious life form and they soon realize that its life cycle has merely begun.','Alien, el 8° pasajero',1979,1);
insert into movie (id_movie,duration,synopsis,title,year,id_type_movie) VALUES(2,'2:00','A small-time boxer gets a supremely rare chance to fight a heavy-weight champion in a bout in which he strives to go the distance for his self-respect.','Rocky',1976,4);
insert into movie (id_movie,duration,synopsis,title,year,id_type_movie) VALUES(3,'3:01','After the devastating events of Avengers: Infinity War (2018), the universe is in ruins. With the help of remaining allies, the Avengers assemble once more in order to reverse Thanos actions and restore balance to the universe.','Avengers: Endgame ',2019,1);
insert into movie (id_movie,duration,synopsis,title,year,id_type_movie) VALUES(4,'1:59','Rocky struggles in family life after his bout with Apollo Creed, while the embarrassed champ insistently goads him to accept a challenge for a rematch.','Rocky II',1979,4);
insert into movie (id_movie,duration,synopsis,title,year,id_type_movie) VALUES(5,'1:49','After splitting with the Joker, Harley Quinn joins superheroes Black Canary, Huntress and Renee Montoya to save a young girl from an evil crime lord.','Aves de presa y la fantabulosa emancipación de una Harley Quinn',2020,7);
insert into movie (id_movie,duration,synopsis,title,year,id_type_movie) VALUES(6,'1:43','Anna, Elsa, Kristoff, Olaf and Sven leave Arendelle to travel to an ancient, autumn-bound forest of an enchanted land. They set out to find the origin of Elsas powers in order to save their kingdom.','Frozen II',2019,9);
insert into movie (id_movie,duration,synopsis,title,year,id_type_movie) VALUES(7,'2:06','Two teenage cancer patients begin a life-affirming journey to visit a reclusive author in Amsterdam.','Bajo la Misma Estrella',2014,9);
insert into movie (id_movie,duration,synopsis,title,year,id_type_movie) VALUES(8,'1:37','Two American college students on a walking tour of Britain are attacked by a werewolf that none of the locals will admit exists.','El hombre lobo en Londres',1980,3);
insert into movie (id_movie,duration,synopsis,title,year,id_type_movie) VALUES(9,'2:06','After being held captive in an Afghan cave, billionaire engineer Tony Stark creates a unique weaponized suit of armor to fight evil.','Iron man - El hombre de hierro',2008,1);
insert into movie (id_movie,duration,synopsis,title,year,id_type_movie) VALUES(10,'2:10','Imprisoned on the planet Sakaar, Thor must race against time to return to Asgard and stop Ragnarök, the destruction of his world, at the hands of the powerful and ruthless villain Hela.','Thor: Ragnarok',2017,1);


insert into actor (id_actor,name,last_name,age) values (1,'Sigourney','Weaver',71);
insert into actor (id_actor,name,last_name,age) values (2,'Sylvester','Stallone',72);
insert into actor (id_actor,name,last_name,age) values (3,'Robert','Downey Jr',55);
insert into actor (id_actor,name,last_name,age) values (4,'Chris','Hemsworth',37);
insert into actor (id_actor,name,last_name,age) values (5,'Margot','Robbie',30);
insert into actor (id_actor,name,last_name,age) values (6,'Kristen','Bell',40);
insert into actor (id_actor,name,last_name,age) values (7,'Shailene','Woodley',29);
insert into actor (id_actor,name,last_name,age) values (8,'David','Naughton',61);

insert into address_actor (id_address,address,id_actor) values (1,'My first address-EU',1);
insert into address_actor (id_address,address,id_actor) values (2,'My second address-Italy',1);
insert into address_actor (id_address,address,id_actor) values (3,'Boston av',2);
insert into address_actor (id_address,address,id_actor) values (4,'New York,Cenyral park',2);
insert into address_actor (id_address,address,id_actor) values (5,'Other address',2);
insert into address_actor (id_address,address,id_actor) values (6,'LA av',3);
insert into address_actor (id_address,address,id_actor) values (7,'Santa clara av',4);
insert into address_actor (id_address,address,id_actor) values (8,'Nuevo Mexico Av',5);
insert into address_actor (id_address,address,id_actor) values (9,'main address',6);
insert into address_actor (id_address,address,id_actor) values (10,'silicon valley',7);
insert into address_actor (id_address,address,id_actor) values (11,'sn fco av',8);

insert into movie_actor (id_movie,id_actor) values (1,1);
insert into movie_actor (id_movie,id_actor) values (2,2);
insert into movie_actor (id_movie,id_actor) values (3,3);
insert into movie_actor (id_movie,id_actor) values (3,4);
insert into movie_actor (id_movie,id_actor) values (4,2);
insert into movie_actor (id_movie,id_actor) values (5,5);
insert into movie_actor (id_movie,id_actor) values (6,6);
insert into movie_actor (id_movie,id_actor) values (7,7);
insert into movie_actor (id_movie,id_actor) values (8,8);
insert into movie_actor (id_movie,id_actor) values (9,3);
insert into movie_actor (id_movie,id_actor) values (10,4);
```
### Ahora vamos a realizar las siguientes modificaciones en Movie, MovieRepository, MovieTest
``` js
package com.neosuniversity.videolibrary.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString(exclude = { "synopsis"})
@Table(name="MOVIE",schema="VIDEODB")
public class Movie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_MOVIE",nullable=false,unique=false)
	private Long idMovie;
	
	@OneToOne(cascade=CascadeType.DETACH,fetch=FetchType.EAGER)
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
	
	@ManyToMany(cascade=CascadeType.DETACH,fetch=FetchType.EAGER)
	@JoinTable(name="MOVIE_ACTOR",joinColumns=@JoinColumn(name="ID_MOVIE"),
	inverseJoinColumns=@JoinColumn(name="ID_ACTOR"))
	@OrderBy(value="idActor ASC")
	private Set<Actor> actors = new HashSet<>();

}
```
``` js
package com.neosuniversity.videolibrary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.neosuniversity.videolibrary.entities.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

	@Query("SELECT m FROM Movie m Where m.title LIKE CONCAT('%',:title,'%')")
	public List<Movie> findMovieByTitle(@Param("title") String title);

}
```
``` js
public void readAllMoviesTest() {
		log.info("----------------------------------------------");
		log.info("READ ALL MOVIES::::");

		List<Movie> movies = movieRepository.findAll();

		if (Optional.ofNullable(movies).isPresent()) {
			movies.forEach(System.out::println); 
			log.info("----------------------------------------------");
			
		} else {
			log.info("Not Found Movies in DB");
			log.info("----------------------------------------------");
		}

	}
	public void readAllMoviesOrderByTest(String title) {
		log.info("----------------------------------------------");
		log.info("READ MOVIES BY TITLE::::");

		List<Movie> movies = movieRepository.findMovieByTitle(title);

		if (Optional.ofNullable(movies).isPresent()) {
			movies.forEach(System.out::println); 
			log.info("----------------------------------------------");
			
		} else {
			log.info("Not Found Movies in DB");
			log.info("----------------------------------------------");
		}

	}
```
### Por ultimo ejecutar VideoLibraryApplication.java con los siguientes metodos:
``` js
//		movieTest.readAllMoviesTest();
//		movieTest.readAllMoviesOrderByTest("lobo");
```