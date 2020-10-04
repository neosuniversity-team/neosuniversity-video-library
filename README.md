## Spring Rest entidad Type of Movie

En esta práctica aplicará los conocimientos aprendidos acerca de servicios Rest utilizando peticiones:
* GET
* POST
* PUT
* DELETE

Tambien conceptos de validación y el manejo de ResponseEntity para las Response de nuestros servicios Rest

## Nota: Como prerequisito es necesario borrar todo lo que tengamos en nuestro esquema videodb y ejecutar nuestro script populate.sql

### Listado de archivos:
### Agregar dependencias en el archivo pom.xml
``` js
<dependencies>
.......
.......
<!--  javax.validation by Entities -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>
.......
.......
</dependencies>
```
### Agregar la siguientes lineas para poder hacer validacion en la entidad TypeMovie
``` js
import javax.validation.constraints.NotEmpty;
.......
.......
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="ID_TYPE_MOVIE",nullable=false)
private Long idTypeMovie;
	
@Column(name="TYPE_DESCRIPTION",length=100,nullable=false)
@NotEmpty(message = "Please provide a description by type of movie")
private String type;
.......
.......
```

### Crear la interface TypeMovieBusinessI con los siguientes metodos
``` js
package com.neosuniversity.videolibrary.business;

import java.util.List;
import com.neosuniversity.videolibrary.entities.TypeMovie;

public interface TypeMovieBusinessI {
	
	public void createTypeMovie(TypeMovie typeMovie);

	public TypeMovie readTypeMovie(Long idTypeMovie);
	
	public void updateTypeMovie(TypeMovie typeMovie);
		
	public void deleteTypeMovieTest(Long idTypeMovie);
	
	public List<TypeMovie> readTypeMovieAll();
}
```
### Crear la implementacion TypeMovieBusiness de la interface TypeMovieBusinessI
``` js
package com.neosuniversity.videolibrary.business;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.neosuniversity.videolibrary.entities.TypeMovie;
import com.neosuniversity.videolibrary.repository.TypeMovieRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TypeMovieBusiness implements TypeMovieBusinessI {
	@Autowired
	private TypeMovieRepository typeMovieRepository;

	public void createTypeMovie(TypeMovie typeMovie) {

		log.info("----------------------------------------------");
		log.info("SAVE TYPE MOVIE::::");

		typeMovieRepository.save(typeMovie);
		log.info("----------------------------------------------");
	}
	
	public TypeMovie readTypeMovie(Long idTypeMovie) {
		log.info("----------------------------------------------");
		log.info("READ TYPE MOVIE::::");

		Optional<TypeMovie> typeMovie = typeMovieRepository.findById(idTypeMovie);

		if (typeMovie.isPresent()) {
			log.info("----------------------------------------------");
			return typeMovie.get();
		} else {
			log.info("Not Found typeMovie: " + idTypeMovie);
			log.info("----------------------------------------------");
			return null;
		}
	}
	
	public void updateTypeMovie(TypeMovie typeMovie) {
		log.info("----------------------------------------------");
		log.info("UPDATE TYPE MOVIE::::");

		TypeMovie typeMovieRead = readTypeMovie(typeMovie.getIdTypeMovie());

		if (Optional.ofNullable(typeMovieRead).isPresent()) {
			typeMovieRead.setType(typeMovie.getType());

			typeMovieRepository.save(typeMovieRead);
			log.info("----------------------------------------------");
		} else {
			log.info("Not Update typeMovie: " + typeMovie.getType());
			log.info("----------------------------------------------");
		}
	}
	
	public void deleteTypeMovieTest(Long idTypeMovie) {
		log.info("----------------------------------------------");
		log.info("DELETE TYPE MOVIE::::");

		TypeMovie typeMovie = readTypeMovie(idTypeMovie);

		if (Optional.ofNullable(typeMovie).isPresent()) {
			typeMovieRepository.delete(typeMovie);
			log.info("----------------------------------------------");
		} else {
			log.info("Not Delete TypeMovie: " + idTypeMovie);
			log.info("----------------------------------------------");
		}
	}
	
	@Override
	public List<TypeMovie> readTypeMovieAll() {
		log.info("----------------------------------------------");
		log.info("GET ALL TYPE MOVIES::::");
		return typeMovieRepository.findAll();
	}
}
```
### Crear el servicio TypeMovieRestController para Type of Movie
``` js
package com.neosuniversity.videolibrary.controllers;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.neosuniversity.videolibrary.business.TypeMovieBusinessI;
import com.neosuniversity.videolibrary.entities.TypeMovie;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/neosuniversity/typeMovies")
public class TypeMovieRestController {
	
	@Autowired
	private TypeMovieBusinessI typeMovieBusinessI;
	
	@PostMapping(consumes = "application/json",produces = "application/json")
	public ResponseEntity<TypeMovie> createTypeMovie(@Valid @RequestBody TypeMovie typeMovie) {
		
		log.info(typeMovie.toString());
		
		typeMovieBusinessI.createTypeMovie(typeMovie);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{idTypeMovie}")
                                    .buildAndExpand(typeMovie.getIdTypeMovie())
                                    .toUri();
        return ResponseEntity.created(location).body(typeMovie);
	}

	@GetMapping(path="/{idTypeMovie}", produces = "application/json")
	public ResponseEntity<TypeMovie> readTypeMovieById(@PathVariable("idTypeMovie") Long idTypeMovie) {
		
		return ResponseEntity.ok()
				.header("idCountry",String.valueOf(idTypeMovie))
				.body(typeMovieBusinessI.readTypeMovie(idTypeMovie));
	}
	
	@PutMapping(consumes = "application/json",produces = "application/json")
	public ResponseEntity<TypeMovie> updateTypeMovie(@Valid @RequestBody TypeMovie typeMovie) {
		
		log.info(typeMovie.toString());
		
		typeMovieBusinessI.updateTypeMovie(typeMovie);
       
		return ResponseEntity.ok()
		.header("IdTypeMovie",String.valueOf(typeMovie.getIdTypeMovie()))
		.body(typeMovieBusinessI.readTypeMovie(typeMovie.getIdTypeMovie()));
	}
	
	@DeleteMapping(path="/{idTypeMovie}",produces = "application/json")
	public ResponseEntity<?> deleteTypeMovie(@PathVariable("idTypeMovie") Long idTypeMovie) {
		
		typeMovieBusinessI.deleteTypeMovieTest(idTypeMovie);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(idTypeMovie)
                .toUri();
       
		return ResponseEntity.ok().location(location).build();
	}
	
	@GetMapping( produces = "application/json")
	public ResponseEntity<List<TypeMovie>> readTypeMovieAll() {
		
		return ResponseEntity.ok()
				.body(typeMovieBusinessI.readTypeMovieAll());
	}
	
}
```
### Configurar la seguridad en WebSecurityConfig.java para permitir la ejecución de nuestros metodos Rest
``` js
.......
.......
 @Override
   protected void configure(HttpSecurity http) throws Exception {
	     
      http
         .authorizeRequests()
            .antMatchers("/", "/index","/signup","/api/v1/**").permitAll()
            .antMatchers("/video/showNewMovie").hasAnyAuthority("ROLE_ADMIN")
            .anyRequest().authenticated()
            .and()
         .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/autentica")
            .usernameParameter("usuario")
            .permitAll()
            .and()
            .logout()
            .permitAll()
            .and()
  	      .exceptionHandling().accessDeniedPage("/accessDenied");
	   
	   http.csrf().disable();
   }
.......
.......
```
