<!doctype html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<title>Demo</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">



<link rel="stylesheet" type="text/css" href="/css/login.css">
</head>
<body>


	<div class="wrapper fadeInDown">
		<div id="formContent">
			<!-- Tabs Titles -->

			<!-- Icon -->
			<div class="fadeIn first">
				<img src="/image/login.png" id="icon" alt="User Icon" />
			</div>


			<!-- Login Form -->
			<form action="/autentica" method="post">
				<input type="text" id="usuario" class="fadeIn second" name="usuario"
					placeholder="login"> <input type="password" id="password"
					class="fadeIn third" name="password" placeholder="password">
				<input type="submit" class="fadeIn fourth" value="Log In">

			</form>

			<!-- Remind Passowrd -->
			<div id="formFooter">
				<a class="underlineHover" href="#">No estás registrado?</a>
			</div>

		</div>
	</div>


</body>
</html>