<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
		
		<a href="/video/showNewMovie" class="btn btn-primary " role="button" aria-pressed="true">Agregar Nueva Pelicula</a>
		
		</div>
		
		<div class="row  mt-4">
		
		<br>

			<h4>Lista de Pel�culas</h4>

			<table class="table table-striped mt-3">
				<thead class="thead-dark">
					<tr>
						<th scope="col">#</th>
						<th scope="col">T�tulo</th>
						<th scope="col">Sinopsis</th>
						<th scope="col">A�o</th>
						<th scope="col">Duraci�n</th>
					</tr>
				</thead>
				<tbody>
				
				
				<c:forEach items="${list}" var="movie">
					<tr>
						<th scope="row">${movie.idMovie}</th>
						<td>${movie.title}</td>
						<td>${movie.synopsis} </td>
							<td>${movie.year}</td>
						<td>${movie.duration}hrs</td>
					</tr>
					</c:forEach>

				</tbody>

			</table>

		</div>

	</div>




<jsp:include page="includes/jsscripts.jsp"></jsp:include>
</body>
</html>