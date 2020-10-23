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
  
<form:form modelAttribute="newUser" method="post" action="/signup" >
  <div class="form-group row">
    <label for="text" class="col-4 col-form-label">Usuario</label> 
    <div class="col-8">
      <form:input  path="username" id="username" name="username" type="text" class="form-control"/>
      
      <div class="text-danger">
      <form:errors path="username"></form:errors>
      </div>
      
    </div>
  </div>
  <div class="form-group row">
    <label for="text1" class="col-4 col-form-label">Password</label> 
    <div class="col-8">
      <form:input path="password" id="password" name="password" 
      type="password" class="form-control"/>
      
       <div class="text-danger">
      <form:errors path="password"></form:errors>
      </div>
    </div>
  </div>
  <div class="form-group row">
    <label for="text2" class="col-4 col-form-label">Confirma Password</label> 
    <div class="col-8">
      <form:input path="passwordConfirm" id="passwordConfirm" name="passwordConfirm"
       type="password" class="form-control"/>
       
       <div class="text-danger">
      <form:errors path="passwordConfirm"></form:errors>
      </div>
    </div>
  </div> 
  <div class="form-group row">
    <div class="offset-4 col-8">
      <button name="submit" type="submit" class="btn btn-primary">Enviar</button>
    </div>
  </div>
</form:form>


</div>

          
         
         </div>

	</div>

<jsp:include page="includes/jsscripts.jsp"></jsp:include>
</body>
</html>
