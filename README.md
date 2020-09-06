# DataBinding con Spring MVC

### Modifique el Controller com.neosuniversity.videolibrary.controllers.VideoController
### Con esta modificación con la URL http://localhost:8080/video/showNewMovie se mostrará el formulario para dar de alta una nueva Película

``` java
package com.neosuniversity.videolibrary.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
		
		movie.setTitle("Mi Pelicula");
		
		model.addAttribute("movie", movie);
		
		return "nuevaPelicula";
		
	}
	
}

```

### Genere una nueva JSP nuevaPelicula.jsp en la ruta src/main/webapp/WEB-INF/

Consejo: Puede utilizar el generador de formularios basado en boostrap https://bootstrapformbuilder.com/

Genere el formulario usando las propiedades title, synopsis, year, duration

``` html


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
         
          
<form>
  <div class="form-group row">
    <label for="title" class="col-4 col-form-label">Título</label> 
    <div class="col-8">
      <input name="title" id="title"  type="text" class="form-control"/>
    </div>
  </div>
  <div class="form-group row">
    <label for="synopsis" class="col-4 col-form-label">Sinopsis</label> 
    <div class="col-8">
      <textarea  id="synopsis" name="synopsis" cols="40" rows="5" class="form-control">
      </textarea>
    </div>
  </div>
  <div class="form-group row">
    <label for="year" class="col-4 col-form-label">Año Lanzamiento</label> 
    <div class="col-8">
      <input   id="year" name="year" type="text" class="form-control"/>
    </div>
  </div>
  <div class="form-group row">
    <label for="duration" class="col-4 col-form-label">Duración de la película</label> 
    <div class="col-8">
      <input  id="duration" name="duration" type="text" class="form-control"/>
    </div>
  </div> 
  <div class="form-group row">
    <div class="offset-4 col-8">
      <button name="submit" type="submit" class="btn btn-danger">Enviar</button>
      <a href="/video/lista" class="btn btn-danger " role="button" aria-pressed="true">Regresar</a>
    </div>
  </div>
</form>

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


### Ahora modifiquela para agregar los tags de Spring

 - Como puntos importantes recuerde que debe agregar la librería de etiquetas Spring Form a la JSP
 - Usar las etiquetas <form:form> <form:input> ,etc
 
 Mayor información en la documentación de Spring MVC
 
 https://docs.spring.io/spring/docs/4.2.x/spring-framework-reference/html/spring-form-tld.html
 

```html

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
    </div>
  </div>
  <div class="form-group row">
    <label for="synopsis" class="col-4 col-form-label">Sinopsis</label> 
    <div class="col-8">
      <form:textarea  path="synopsis" id="synopsis" name="synopsis" cols="40" rows="5" class="form-control"></form:textarea>
    </div>
  </div>
  <div class="form-group row">
    <label for="year" class="col-4 col-form-label">Año Lanzamiento</label> 
    <div class="col-8">
      <form:input path="year"  id="year" name="year" type="text" class="form-control"/>
    </div>
  </div>
  <div class="form-group row">
    <label for="duration" class="col-4 col-form-label">Duración de la película</label> 
    <div class="col-8">
      <form:input path="duration" id="duration" name="duration" type="text" class="form-control"/>
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

### Como último punto es necesario colocar un @RequestMapping en el controller VideoController esto con el objetivo
## de procesar la información del formulario

### com.neosuniversity.videolibrary.controllers.VideoController

``` java

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


```

