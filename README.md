## Codigo final- Spring Rest Movie
En esta práctica aplicará los conocimientos aprendidos acerca de servicios Rest utilizando peticiones:
* GET
* POST
* PUT
* DELETE

Tambien conceptos de validación y el manejo de ResponseEntity para las Response de nuestros servicios Rest

### Crear la interface MovieBusinessI con los siguientes metodos
``` js
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.neosuniversity.videolibrary.entities.Actor;
import com.neosuniversity.videolibrary.entities.Movie;
import com.neosuniversity.videolibrary.entities.TypeMovie;

public interface MovieBusinessI {

	void createMovie(Movie movie);

	Movie readMovie(Long idMovie);

	void updateMovie(Movie movie);

	void deleteMovie(Long idMovie);

	List<Movie> readMovieAll();

	default void validateMovieData(Movie movie, Movie movieDB) {

		if (!validateString(movie.getTitle())) {
			movieDB.setTitle(movie.getTitle());
		}
		if (!validateInteger(movie.getYear())) {
			movieDB.setYear(movie.getYear());
		}
		if (!validateString(movie.getSynopsis())) {
			movieDB.setSynopsis(movie.getSynopsis());
		}
		if (!validateString(movie.getDuration())) {
			movieDB.setDuration(movie.getDuration());
		}
		movieDB.setTypemovie(movie.getTypemovie());
		
		if (!validateNullActors(movie.getActors())) {
			movieDB.setActors(movie.getActors());
		}
	}

	default boolean validateString(String value) {
		value = StringUtils.defaultString(value);
		if (StringUtils.isEmpty(value) && StringUtils.isBlank(value)) {
			return true;
		} else {
			return false;
		}

	}

	default boolean validateInteger(int value) {
		if (value == 0) {
			return true;
		} else {
			return false;
		}
	}
	default boolean validateNullTypeMovie(TypeMovie typeMovie) {
		if(Optional.ofNullable(typeMovie).isEmpty()) {
			return true;
		}else {
			return false;
		}
	}
	default  boolean validateNullActors(Set<Actor> actors) {
		if (Optional.ofNullable(actors).isPresent()) {
			return false;
		}else {
			return true;
		}
	}
}
```
### Crear la implementacion MovieBusiness de la interface MovieBusinessI
``` js
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neosuniversity.videolibrary.entities.Movie;
import com.neosuniversity.videolibrary.repository.MovieRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MovieBusiness  implements MovieBusinessI {

	@Autowired
	private MovieRepository movieRepository;
	
	
	@Override
	public void createMovie(Movie movie) {
		log.info("----------------------------------------------");
		log.info("SAVE MOVIE::::");

		movieRepository.save(movie);
		log.info("----------------------------------------------");

	}

	@Override
	public Movie readMovie(Long idMovie) {
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

	@Override
	public void updateMovie(Movie movie) {
		log.info("----------------------------------------------");
		log.info("UPDATE ACTOR::::");
		movieRepository.findById(movie.getIdMovie()).map(movieDB -> {
			validateMovieData(movie, movieDB);
			return movieRepository.save(movieDB);
		});
	}

	
	@Override
	public void deleteMovie(Long idMovie) {
		log.info("----------------------------------------------");
		log.info("DELETE MOVIE::::");

		Movie movie = readMovie(idMovie);

		if (Optional.ofNullable(movie).isPresent()) {
			movieRepository.delete(movie);
			log.info("----------------------------------------------");
		} else {
			log.info("Not Delete Movie: " + idMovie);
			log.info("----------------------------------------------");
		}

	}

	@Override
	public List<Movie> readMovieAll() {
		log.info("----------------------------------------------");
		log.info("GET ALL Movies::::");
		return movieRepository.findAll();
	}

}
```
### Por ultimo vamos a crear los servicios Rest para la entidad Movie - MovieRestController
``` js
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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

import com.neosuniversity.videolibrary.business.MovieBusinessI;
import com.neosuniversity.videolibrary.entities.Movie;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/neosuniversity/movies")
public class MovieRestController {
	
	@Autowired
	private MovieBusinessI movieBusinessI;
	
	@PostMapping(consumes = "application/json",produces = "application/json")
	public ResponseEntity<Movie> createMovie(@Valid @RequestBody Movie movie) {
		
		log.info(movie.toString());
		
		movieBusinessI.createMovie(movie);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                                    .path("/{idActor}")
                                    .buildAndExpand(movie.getIdMovie())
                                    .toUri();
        return ResponseEntity.created(location).body(movie);
	}
	
	@GetMapping(path="/{idMovie}", produces = "application/json")
	public ResponseEntity<Movie> readMovieById(@PathVariable("idMovie") Long idMovie) {
		
		return ResponseEntity.ok()
				.header("idMovie",String.valueOf(idMovie))
				.body(movieBusinessI.readMovie(idMovie));
	}
	
	@GetMapping( produces = "application/json")
	public ResponseEntity<List<Movie>> readMovieAll() {
		
		return ResponseEntity.ok()
				.body(movieBusinessI.readMovieAll());
	}
	

	@PutMapping(consumes = "application/json",produces = "application/json")
	public ResponseEntity<Movie> updateMovie(@Valid @RequestBody Movie movie) {
		
		log.info(movie.toString());
		
		movieBusinessI.updateMovie(movie);
       
		return ResponseEntity.ok()
		.header("IdMovie",String.valueOf(movie.getIdMovie()))
		.body(movieBusinessI.readMovie(movie.getIdMovie()));
	}
	
	@DeleteMapping(path="/{idMovie}",produces = "application/json")
	public ResponseEntity<?> deleteMovie(@PathVariable("idMovie") Long idMovie) {
		
		movieBusinessI.deleteMovie(idMovie);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .buildAndExpand(idMovie)
                .toUri();
       
		return ResponseEntity.ok().location(location).build();
	}
}
```
