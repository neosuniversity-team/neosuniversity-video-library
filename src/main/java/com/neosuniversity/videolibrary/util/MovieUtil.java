
package com.neosuniversity.videolibrary.util;

import java.util.Date;

import com.neosuniversity.videolibrary.entities.Movie;

public interface MovieUtil {

	String value = "";

	public static Movie createMovieMockup() {

		Movie movie = new Movie();
		movie.setTitle("Alien, the 8° passenger");
		movie.setYear(1979);
		movie.setSynopsis("After a space merchant vessel receives an unknown "
				+ "transmission as a distress call, one of the crew is attacked "
				+ "by a mysterious life form and they soon realize that its life " 
				+ "cycle has merely begun.");
		movie.setDuration(new Date());

		return movie;
	}

	public static Movie updateMovieMockup() {

		Movie movie = new Movie();
		movie.setTitle("Alien");
		movie.setYear(1978);
		movie.setSynopsis("After a space merchant vessel receives an unknown "
				+ "transmission as a distress call");
		movie.setDuration(new Date());

		return movie;
	}
}
