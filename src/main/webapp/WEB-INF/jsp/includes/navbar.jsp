<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

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
			
			<security:authorize access="isAuthenticated()">
	    
			 <ul class="navbar-nav ml-auto nav-flex-icons">
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink-333" data-toggle="dropdown"
          aria-haspopup="true" aria-expanded="false">
          <i class="fas fa-user"></i>
        </a>
        <div class="dropdown-menu dropdown-menu-right dropdown-default"
          aria-labelledby="navbarDropdownMenuLink-333">
          <a class="dropdown-item" href="#"><security:authentication property="principal.username" /> </a>
          <a class="dropdown-item" href="/logout">Logout</a>
         
        </div>
      </li>
    </ul>

	</security:authorize>
			
		</div>
		
	</nav>