# Spring Validation

###Modifique su pom.xml agregando la dependencia de validación

###pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.3.2.RELEASE</version>
		<relativePath /> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.neosuniversity</groupId>
	<artifactId>video-library</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>video-library</name>
	<description>Video Library System</description>

	<properties>
		<java.version>11</java.version>
	</properties>

	<dependencies>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<!-- add features by persistence DAO layer -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<!-- add connection with mysql DB -->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>

		<!-- Validaciones JSR 303 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>

		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
			<exclusions>
				<exclusion>
					<groupId>org.junit.vintage</groupId>
					<artifactId>junit-vintage-engine</artifactId>
				</exclusion>
			</exclusions>
		</dependency>

		<!-- config JSP -->

		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
		</dependency>
		<dependency>
			<groupId>org.apache.tomcat.embed</groupId>
			<artifactId>tomcat-embed-jasper</artifactId>
			<scope>provided</scope>
		</dependency>

	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>3.8.0</version>
				<configuration>
					<release>11</release>
				</configuration>
			</plugin>
		</plugins>
	</build>

</project>

```

### Modifique la clase Java Movie para poder agregar los constraints
### com.neosuniversity.videolibrary.entities.Movie

``` java

package com.neosuniversity.videolibrary.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString(exclude = { "synopsis"})
@Table(name="MOVIE",schema="VIDEODB")
public class Movie {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_MOVIE",nullable=false,unique=false)
	private Long idMovie;
	
	@OneToOne(cascade=CascadeType.DETACH,fetch=FetchType.EAGER)
	@JoinColumn(name = "ID_TYPE_MOVIE")
	private TypeMovie typemovie;
	
	//add validation
    
	@NotBlank(message = "Ingrese un valor en el título")
	@Size(min =  1, max= 200, message = "La longitud máxima es de 200")
	@Column(name="TITLE",length=200,nullable=false)
	private String title;
	
	@NotNull(message = "Ingrese un valor al año")
	@Column(name="YEAR",nullable=false)
	private Integer year;
	
	@Column(name="SYNOPSIS",length=900,nullable=true)
	@NotBlank(message="Ingrese una descripción a la sinopsis")
	private String synopsis;
	
	@Column(name="DURATION",nullable=true)
	@Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Ingrese la duración en el formato HH:mm")
	private String duration;
	
	@ManyToMany(cascade=CascadeType.DETACH,fetch=FetchType.EAGER)
	@JoinTable(name="MOVIE_ACTOR",joinColumns=@JoinColumn(name="ID_MOVIE"),
	inverseJoinColumns=@JoinColumn(name="ID_ACTOR"))
	@OrderBy(value="idActor ASC")
	private Set<Actor> actors = new HashSet<>();

}
```

###Modifique el controller VideoController para poder ejecutar los constraints que agregó en el apartado anterior
### src/main/java/com/neosuniversity/videolibrary/controllers/VideoController.java

``` java

package com.neosuniversity.videolibrary.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
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
	public String newMovie(@ModelAttribute("movie") @Valid Movie movie, BindingResult bindingResult) {
		
		log.debug("movie: " + movie);
		
		 if(bindingResult.hasErrors()){
	            return "nuevaPelicula";
	     }
		
		movieRepo.save(movie);
		
		return "forward:/video/lista";
	}

}

```


### Modifique la JSP de alta de pelicula para agregar las tags <form:errors> donde se desplegarán los errores de validación

``` html
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>Document</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">

</head>
<body>

	<!-- https://getbootstrap.com/docs/4.0/components/navbar/ -->

	<!-- nav bar dark 
https://www.w3schools.com/bootstrap4/bootstrap_navbar.asp
-->

	<nav class="navbar navbar-expand-sm bg-primary navbar-dark">
		<a class="navbar-brand" href="#">VideoLibrary</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNav" aria-controls="navbarNav"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<li class="nav-item active"><a class="nav-link"
					href="/video/lista">Lista de Peliculas <span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item"><a class="nav-link" href="#">Acerca de</a>
				</li>

			</ul>
		</div>
	</nav>

	<div class="container">
   
   
         <div class="row mt-4">
         
         <div class="col-9">
         
          
<form:form action="/video/saveMovie" modelAttribute="movie" autocomplete="false">
  <div class="form-group row">
    <label for="title" class="col-4 col-form-label">Título</label> 
    <div class="col-8">
      <form:input path="title"  type="text" class="form-control"/>
      
     <div class="text-danger">
          <form:errors path="title" class="control-label" />
      </div>
     
    </div>
  </div>
  <div class="form-group row">
    <label for="synopsis" class="col-4 col-form-label">Sinopsis</label> 
    <div class="col-8">
      <form:textarea  path="synopsis" id="synopsis" name="synopsis" cols="40" rows="5" class="form-control"></form:textarea>
      
       <div class="text-danger">
          <form:errors path="synopsis" class="control-label" />
      </div>
    </div>
  </div>
  <div class="form-group row">
    <label for="year" class="col-4 col-form-label">Año Lanzamiento</label> 
    <div class="col-8">
      <form:input path="year"  id="year" name="year" type="text" class="form-control"/>
      
      <div class="text-danger">
          <form:errors path="year" class="control-label" />
      </div>
    </div>
  </div>
  <div class="form-group row">
    <label for="duration" class="col-4 col-form-label">Duración de la película</label> 
    <div class="col-8">
      <form:input path="duration" id="duration" name="duration" type="text" class="form-control"/>
      <div class="text-danger">
          <form:errors path="duration" class="control-label" />
      </div>
    </div>
  </div> 
  <div class="form-group row">
    <div class="offset-4 col-8">
      <button name="submit" type="submit" class="btn btn-danger">Enviar</button>
      <a href="/video/lista" class="btn btn-danger " role="button" aria-pressed="true">Regresar</a>
    </div>
  </div>
</form:form>

</div>

          
         
         </div>

	</div>



	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
		integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
		integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV"
		crossorigin="anonymous"></script>

	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"
		integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN"
		crossorigin="anonymous"></script>
</body>
</html>


```
