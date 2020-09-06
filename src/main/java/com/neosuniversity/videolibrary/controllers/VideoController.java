package com.neosuniversity.videolibrary.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neosuniversity.videolibrary.entities.Movie;
import com.neosuniversity.videolibrary.repository.MovieRepository;


import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/video")
public class VideoController {
	
	@Autowired
	private MovieRepository movieRepo;
	
	@RequestMapping("/lista")
	public String showList(ModelMap model) {
		
		List<Movie> list = movieRepo.findAll();
		
		model.addAttribute("list", list);
		
		return "listPeliculas";
		
		
	}
	
	@RequestMapping("/showNewMovie")
	public String showNewMovie(ModelMap model) {
		
		Movie movie = new Movie();
		
		movie.setTitle("John Wick");
		
		model.addAttribute("movie", movie);
		
		return "nuevaPelicula";
		
	} 
	
	@RequestMapping("/saveMovie")
	public String newMovie(@ModelAttribute("movie") Movie movie) {
		
		log.debug("movie: " + movie);
		
		movieRepo.save(movie);
		
		return "forward:/video/lista";
	}

}
