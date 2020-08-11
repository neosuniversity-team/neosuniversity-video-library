## Spring JPA Mapeo One to Many (Actor - Address)

En esta práctica aprendera a realizar el mapeo One to Many utilizando las entidades Actor y Address

### Realizar los siguientes pasos:

### Crear entidad Address
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
@Table(name="ADDRESS_ACTOR",schema="VIDEODB")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ADDRESS", nullable = false)
	private Long idAddress;

	@Column(name = "ADDRESS", length = 200, nullable = false)
	private String address;
	
}
```
### Crear AddressRepository
``` js
package com.neosuniversity.videolibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neosuniversity.videolibrary.entities.Actor;


public interface ActorRepository extends JpaRepository <Actor, Long>{

}
```
### Modificar Entidad Actor - Mapeo one to Many
``` js
package com.neosuniversity.videolibrary.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Table(name = "ACTOR", schema = "VIDEODB")
@Entity
public class Actor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ACTOR", nullable = false)
	private Long idActor;

	@Column(name = "NAME", length = 200, nullable = false)
	private String name;

	@Column(name = "LAST_NAME", length = 200, nullable = false)
	private String lasname;

	@Column(name = "AGE", nullable = true)
	private int age;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_ACTOR")
	private List<Address> addresses= new ArrayList<>();
	

}
```
### Modificar ActorTest
``` js
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
	public void updateActorAndAddressTest(Long idActor) {

		log.info("----------------------------------------------");
		log.info("UPDATE ACTOR::::");

		Actor readActor = readActorTest(idActor);
		Actor updateActor = ActorUtil.updateActorMockup();

		if (Optional.ofNullable(readActor).isPresent()) {
			//update actor
			readActor.setName(updateActor.getName());
			readActor.setLasname(updateActor.getLasname());
			
			readActor.setAge(updateActor.getAge());
			//Add new address
			if (Optional.ofNullable(readActor.getAddresses()).isPresent()) {
				Address address1 = new Address();
				List<Address> addresses= readActor.getAddresses();
				address1.setAddress("My-update Address");
				addresses.add(address1);
				readActor.setAddresses(addresses);
			}
			
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
```
### Modificar Ejecucion de VideoLibraryApplication
``` js
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
//		actorTest.createActorAndAddressTest();
//		actorTest.readActorTest(idActor);
//		actorTest.updateActorAndAddressTest(idActor);
//		actorTest.updateActorTest(idActor);
//		actorTest.deleteActorTest(idActor);
		
//		typeMovie.createTypeMovieTest();
//		typeMovie.readTypeMovieTest(idTypeMovie);
//		typeMovie.updateTypeMovieTest(idTypeMovie,"COMEDY");
//		typeMovie.deleteTypeMovieTest(idTypeMovie);	
	}

}
```