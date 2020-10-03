# Spring Security  

## Configuración del Framework y autenticación in Memory

Es necesario agregar una dependencia al pom.xml

``` xml 

<!-- Security -->

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>

```

La configuración completa del pom.xml es: 

``` xml

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.3.2.RELEASE</version>
		<relativePath /> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.neosuniversity</groupId>
	<artifactId>video-library</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>video-library</name>
	<description>Video Library System</description>

	<properties>
		<java.version>11</java.version>
	</properties>

	<dependencies>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<!-- add features by persistence DAO layer -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-jpa</artifactId>
		</dependency>
		<!-- add connection with mysql DB -->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency>

		<!-- Validaciones JSR 303 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-validation</artifactId>
		</dependency>

	   <!-- Security -->

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>

		<dependency>
			<groupId>org.projectlombok</groupId>
			<artifactId>lombok</artifactId>
			<optional>true</optional>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
			<exclusions>
				<exclusion>
					<groupId>org.junit.vintage</groupId>
					<artifactId>junit-vintage-engine</artifactId>
				</exclusion>
			</exclusions>
		</dependency>

		<!-- config JSP -->

		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
		</dependency>
		<dependency>
			<groupId>org.apache.tomcat.embed</groupId>
			<artifactId>tomcat-embed-jasper</artifactId>
			<scope>provided</scope>
		</dependency>

	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-compiler-plugin</artifactId>
				<version>3.8.0</version>
				<configuration>
					<release>11</release>
				</configuration>
			</plugin>
		</plugins>
	</build>

</project>


```

Con esta configuración es posible tener un mecanismo de autenticación muy básico en donde se despliega un usuario en la consola, 
ejecute el proyecto con este cambio y podrá ver que se genera una página en automático para hacer la autenticación


Una vez que ha probado la autenticación más básica, el siguiente paso es hacer una autenticación utilizanfo la  página de login.jsp que desarrollamos en el tema de Spring MVC
y ahora nos servirá para autenticar a los usuarios.


Es necesario agregar un a nueva clase llamada WebSecurityConfig 

El código completo de com.neosuniversity.videolibrary.security.WebSecurityConfig

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

	 
   @Override
   protected void configure(HttpSecurity http) throws Exception {
	     
      http
         .authorizeRequests()
            .antMatchers("/", "/index").permitAll()
            .anyRequest().authenticated()
            .and()
         .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/autentica")
            .usernameParameter("usuario")
            .permitAll()
            .and()
            .logout()
            .permitAll();
	   
	   http.csrf().disable();
   }
  
   @Autowired
   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	   auth.inMemoryAuthentication()
       .withUser("user").password(passwordEncoder().encode("user"))
       .authorities("ROLE_USER")
       .and()
       .withUser("admin").password(passwordEncoder().encode("admin"))
       .authorities("ROLE_ADMIN");
       
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


Ahora es necesario agregar un código en la pagina login.jsp para poder indicar al usuario si el usuario/password es erroneo:

``` html

		
	 <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
	    <div class="text-danger">
	       Usuario/Password Incorrecto
	    </div>
	</c:if>
	
```

código completo de login.jsp

``` html
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
				<a class="underlineHover" href="#">No estás registrado?</a>
			</div>

		</div>
	</div>


</body>
</html>

``` 

Ahora agregue una nueva pagina JSP llamada index.jsp esta página servirá para poder probar los recursos que están disponibles sin autenticación


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
					href="/video/lista">Lista de Peliculas <span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item"><a class="nav-link" href="#">Acerca de</a>
				</li>

			</ul>
		</div>
	</nav>

	<div class="container">
		<h4>Pagina Principal de Video Library</h4>

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


Modifique la clase MainController para poder ingresar a la url http://localhost:8080/index 

El código completo de com.neosuniversity.videolibrary.controllers.MainController es

``` java
package com.neosuniversity.videolibrary.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/login")
	public String hello() {
		
		return "login";
		
	}
	
	
	@RequestMapping("/index")
	public String accessDenied() {
		
		return "index";
		
	}

}



```



Ejecute el proyecto y note que la url http://localhost:8080/index esta disponible sin autenticarse





Ahora vamos a configurar una página para restringir el acceso a el alta de pelicula a usuarios  con ROLE_USER 


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

	 
   @Override
   protected void configure(HttpSecurity http) throws Exception {
	     
      http
         .authorizeRequests()
            .antMatchers("/", "/index").permitAll()
            .antMatchers("/video/showNewMovie").hasAnyRole("ROLE_ADMIN")
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
  
   @Autowired
   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	   auth.inMemoryAuthentication()
       .withUser("user").password(passwordEncoder().encode("user"))
       .authorities("ROLE_USER")
       .and()
       .withUser("admin").password(passwordEncoder().encode("admin"))
       .authorities("ROLE_ADMIN");
       
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



