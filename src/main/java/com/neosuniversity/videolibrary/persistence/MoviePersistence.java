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
