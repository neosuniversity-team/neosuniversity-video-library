package com.neosuniversity.videolibrary.util;


import com.neosuniversity.videolibrary.entities.TypeMovie;

public interface TypeMovieUtil {
	
	
	public static TypeMovie createTypeMovieMockup() {

		TypeMovie typeMovie = new TypeMovie();
		typeMovie.setType("Action");
		
		return typeMovie;
	}

	public static TypeMovie updateTypeMovieMockup() {

		TypeMovie typeMovie = new TypeMovie();
		typeMovie.setType("Sci-Fi-II");
		
		return typeMovie;
	}

}
