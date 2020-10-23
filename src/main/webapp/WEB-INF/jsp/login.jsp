<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>Demo</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">

<link rel="stylesheet" href="/css/all.min.css">
<link rel="stylesheet" type="text/css" href="/css/login.css">

</head>
<body>


	<div class="wrapper fadeInDown">
		<div id="formContent">
			<!-- Tabs Titles -->
			
	 <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
	    <div class="text-danger">
	       Usuario/Password Incorrecto
	    </div>
	</c:if>

			<!-- Icon -->
			<div class="fadeIn first mb-2">
				<img src="/image/login.png" id="icon" alt="User Icon" />
			</div>


			<!-- Login Form -->
			<form action="/autentica" method="post">
				<input type="text" id="usuario" class="fadeIn second mt-2"
					name="usuario" placeholder="username"> <input
					type="password" id="password" class="fadeIn third" name="password"
					placeholder="password">
				<!--  	<input type="submit" class="fadeIn fourth" value="Log In"> -->
				<button type="submit" class="btn btn-primary mt-2 mb-2 ">
					<i class="fas fa-sign-in-alt"></i> Log In
				</button>
			</form>

			<!-- Remind Passowrd -->
			<div id="formFooter">
              <a class="underlineHover" href="/signup">No estás registrado?</a>
            </div>

		</div>
	</div>


</body>
</html>