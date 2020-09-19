<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>Document</title>

<jsp:include page="includes/styles.jsp"></jsp:include>

</head>
<body>
<jsp:include page="includes/navbar.jsp"></jsp:include>

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

<jsp:include page="includes/jsscripts.jsp"></jsp:include>
</body>
</html>

