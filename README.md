<<<<<<< HEAD
# Spring MVC -  Envío de información de formularios al controller

El objetivo de la práctica es poder enviar datos de la capa de vista al controller en específico un login básico

### Lista de archivos
### login.jsp

``` html
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>Demo</title>
</head>
<body>

<form action="/autentica" method="post">

   Usuario: 
   <input type="text" name="usuario" >
  Password :
  <input type="password" name="password"> 
  
  <input type="submit" value="Enviar">

</form>
	
</body>
</html>

```

### com.neosuniversity.videolibrary.controllers.Autentica

``` java
package com.neosuniversity.videolibrary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Autentica {

	@RequestMapping(value="/autentica" , method = RequestMethod.POST)
	public String autentica (@RequestParam String usuario, @RequestParam String password) {
		System.out.println(usuario);
		System.out.println(password);
		
		if (!usuario.equalsIgnoreCase("admin")) {
			return "login";
		}
		return "home";
	}
}


```

### Agregar la pagina home.jsp
### src/main/webapp/WEB-INF/jsp/home.jsp

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
					href="/listaPeliculas">Lista de Peliculas <span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item"><a class="nav-link" href="#">Acerca de</a>
				</li>

			</ul>
		</div>
	</nav>

	<div class="container">
		<h4>Bienvenido a VideoLibrary</h4>

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

