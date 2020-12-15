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
