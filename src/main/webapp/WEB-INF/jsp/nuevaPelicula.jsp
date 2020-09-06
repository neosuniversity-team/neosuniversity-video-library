
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