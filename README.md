# Spring Security

## Desarrollo de autenticación utilizando JPA 

Es necesario agregar una nueva entity com.neosuniversity.videolibrary.entities.User

```  java

package com.neosuniversity.videolibrary.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "USER",schema="VIDEODB")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    @Transient
    private String passwordConfirm;

    @ManyToMany(fetch=FetchType.EAGER)
    private Set<Role> roles;

    
}


```

Ahora agrega la entity com.neosuniversity.videolibrary.entities.Role

``` java

package com.neosuniversity.videolibrary.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "ROLE",schema="VIDEODB")
public class Role {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
    
}

```

Ahora agrega un nuevo repository com.neosuniversity.videolibrary.repository.UserRepository

``` java

package com.neosuniversity.videolibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neosuniversity.videolibrary.entities.User;

public interface UserRepository extends JpaRepository<User,Long>{
	
	public User findByUsername(String username);
	
}

``` 

Agrega un nuevo UserDetailsService para poder obtener los datos de los usuarios de la base
de datos y Spring Security pueda autenticarlos.

``` java 

package com.neosuniversity.videolibrary.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.neosuniversity.videolibrary.entities.Role;
import com.neosuniversity.videolibrary.entities.User;
import com.neosuniversity.videolibrary.repository.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
        if (user == null) throw new UsernameNotFoundException(username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    
	}

}

```

Ahora modifique la clase WebSecurityConfig para poder agregar el UserDetailsService en la configuración de Spring Security

``` java

package com.neosuniversity.videolibrary.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	

	@Autowired
	private UserDetailServiceImpl userDetailsService;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}


	 
   @Override
   protected void configure(HttpSecurity http) throws Exception {
	     
      http
         .authorizeRequests()
            .antMatchers("/", "/index").permitAll()
            .antMatchers("/video/showNewMovie").hasAnyAuthority("ROLE_ADMIN")
            .anyRequest().authenticated()
            .and()
         .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/autentica")
            .usernameParameter("usuario")
            .permitAll()
            .and()
            .logout()
            .permitAll()
            .and()
  	      .exceptionHandling().accessDeniedPage("/accessDenied");
	   
	   http.csrf().disable();
   }
  
   
   @Bean
   public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
   }
   
   @Override
   public void configure(WebSecurity web) throws Exception {
   web.ignoring().antMatchers("/css/**", "/webfonts/**", "/image/**");
   }

}

```

Ahora inicie el proyecto para que las tablas User, User_Roles y Role puedan ser creadas por Spring

Una vez que se hayan creado ejecute el script SQL para poder poblar los primeros usarios del sistema

``` sql

INSERT INTO VIDEODB.USER(id,username, password)
values (1,'user','$2a$10$LsG62RqmRHnJW9ovYUM.6e/kl7eIeI/6J7lQgq3n.l/H0gYWZ3c4W');

INSERT INTO VIDEODB.USER(id,username, password)
values (2,'admin','$2a$10$N2kcyD1Us1KCdlqOxy04kedoW0UJr68lj/yGjvBCFviyM0BJ2/Ijm');

INSERT INTO VIDEODB.ROLE(id, name) 
values (1, 'ROLE_USER');

INSERT INTO VIDEODB.ROLE(id, name) 
values (2, 'ROLE_ADMIN');

INSERT INTO VIDEODB.USER_ROLES (users_id, roles_id)
values (1,1);

INSERT INTO VIDEODB.USER_ROLES (users_id, roles_id)
values (2,2);

commit;
``` 


Ahora ingrese a la URL http://localhost:/8080 para poder ingresar los usuarios

Pruebe con el usuario admin/admin, una vez terminadas sus pruebas inicie sesión con el usuario
user/user

## Parte 2

En esta sección de la práctica comprendera el uso de de la tag <jsp:include>

Agregue un nuevo folder en la ruta src/main/webapp/WEB-INF/jsp/includes

Agregue los siguientes archivos navbar.jsp

``` html

<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

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
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" id="navbarDropdownMenuLink-333"
					data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						<i class="fas fa-user"></i>
				</a>
					<div class="dropdown-menu dropdown-menu-right dropdown-default"
						aria-labelledby="navbarDropdownMenuLink-333">
						<a class="dropdown-item" href="#"><security:authentication
								property="principal.username" /> </a> <a class="dropdown-item"
							href="/logout">Logout</a>

					</div></li>
			</ul>

		</security:authorize>

	</div>

</nav>
``` 

Agregue el archivo jsscripts.jsp

``` html
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
``` 

Agregue el archivo styles.jsp

``` html


<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z"
	crossorigin="anonymous">
	
<link rel="stylesheet" href="/css/all.min.css"> 
``` 

Modifique las siguientes páginas home.jsp

``` html

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
		<h4>Bienvenido a VideoLibrary</h4>

	</div>
	
    <jsp:include page="includes/jsscripts.jsp"></jsp:include>

</body>
</html>

```

Modifique el archivo index.jsp

``` html
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
		<h4>Pagina Principal de Video Library</h4>

	</div>

    <jsp:include page="includes/jsscripts.jsp"></jsp:include>
</body>
</html>

```  

Modifique el archivo listPeliculas.jsp

``` html
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


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

		<sec:authorize access="hasAnyAuthority('ROLE_ADMIN')">
			<div class="row mt-4">
				<a href="/video/showNewMovie" class="btn btn-primary " role="button"
					aria-pressed="true">Agregar Nueva Pelicula</a>
			</div>
		</sec:authorize>

		<div class="row  mt-4">
		
		<br>

			<h4>Lista de Películas</h4>

			<table class="table table-striped mt-3">
				<thead class="thead-dark">
					<tr>
						<th scope="col">#</th>
						<th scope="col">Título</th>
						<th scope="col">Sinopsis</th>
						<th scope="col">Año</th>
						<th scope="col">Duración</th>
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

```

Modifique el archivo nuevaPelicula.jsp

``` html
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

 
```