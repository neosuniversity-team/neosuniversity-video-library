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
   
   <div class="alert alert-success" role="alert">
  El usuario se dió de alta exitosamente,  <a href="/login">Ingresar ahora</a>
</div>

	</div>

<jsp:include page="includes/jsscripts.jsp"></jsp:include>
</body>
</html>

