package com.neosuniversity.videolibrary.business;

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
		//if (!validateNullTypeMovie(movie.getTypemovie())) {
			movieDB.setTypemovie(movie.getTypemovie());
		//}
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
