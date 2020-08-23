package com.neosuniversity.videolibrary.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.neosuniversity.videolibrary.entities.Movie;
import com.neosuniversity.videolibrary.repository.MovieRepository;

@Controller
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

}
