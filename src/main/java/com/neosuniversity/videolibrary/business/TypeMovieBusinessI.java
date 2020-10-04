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
