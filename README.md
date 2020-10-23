# Spring Security

## Práctica para poder agregar nuevos usuarios utilizando un formulario de Sign UP


![imagen](https://user-images.githubusercontent.com/561434/96944963-f5bc9880-14a1-11eb-94b4-7468304c9a3f.png)




Es necesario agregar un nuevo validador para que notifique al usuario cuando el password que está ingresando no coincide con la confirmación del password

Esto lo vamos a realizar con la clase com.neosuniversity.videolibrary.validators.PassowordValidator

``` java

package com.neosuniversity.videolibrary.validators;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.neosuniversity.videolibrary.entities.User;

public class PasswordValidator  implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;

		//Solo hace la comparacion si los dos campos no estan vacios
	      if(!user.getPassword().isEmpty() && !user.getPasswordConfirm().isEmpty() &&
	    		  !user.getPassword().equals(user.getPasswordConfirm())) {
	          errors.rejectValue("password", null, "Los passwords no coinciden");
	      }

	}

}


``` 



Es necesario agregar un servicio para poder procesar el alta de un usuario con la clase UserServiceImpl

com.neosuniversity.videolibrary.services.UserServiceImpl

```java 

package com.neosuniversity.videolibrary.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.neosuniversity.videolibrary.entities.Role;
import com.neosuniversity.videolibrary.entities.User;
import com.neosuniversity.videolibrary.repository.RoleRepository;
import com.neosuniversity.videolibrary.repository.UserRepository;

@Service
public class UserServiceImpl {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	public void saveNewUser(User user) {
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPwd  = encoder.encode(user.getPassword());
		user.setPassword(encodedPwd);
		
		Set<Role> roles = roleRepo.findByName("ROLE_USER");
		
		user.setRoles(roles);
		
		userRepo.save(user);
	}

}

``` 



Agregue un nuevo  Controlador com.neosuniversity.videolibrary.controllers.UserController

``` java

package com.neosuniversity.videolibrary.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.neosuniversity.videolibrary.entities.User;
import com.neosuniversity.videolibrary.services.UserServiceImpl;
import com.neosuniversity.videolibrary.validators.PasswordValidator;

@Controller
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
	@GetMapping("/signup")
	public String signup(ModelMap model) {
		
		model.addAttribute("newUser",new User());
		
		
		return "signup";
		
	}
	
	@PostMapping("/signup")
	public String saveNewUser(@ModelAttribute("newUser") @Valid User usuario,BindingResult result) {
		
        PasswordValidator passwordValidator = new PasswordValidator();
        passwordValidator.validate(usuario, result);
		
		if (result.hasErrors()) {
			return "signup";
		}
		
		userService.saveNewUser(usuario);
			
		return "successSignup";
		
	}

}

```

Modifica la entidad usuario para poder realizar la validación  del campo usuario y password desde la página
de alta de usuario.

com.neosuniversity.videolibrary.entities.User

``` java
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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

    @NotBlank(message="Ingrese un usuario")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Su usuario solo debe contener letras mayusculas y/o minusculas")
    private String username;

    @NotBlank(message="Ingrese un password")
    private String password;

    @Transient
    @NotBlank(message="Ingrese un password")
    private String passwordConfirm;

    @ManyToMany(fetch=FetchType.EAGER)
    private Set<Role> roles;

    
}
```

Agregar el repositorio  RoleRepository

com.neosuniversity.videolibrary.repository.RoleRepository

``` java
package com.neosuniversity.videolibrary.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.neosuniversity.videolibrary.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>  {
  
	Set<Role> findByName(String name);
}


```

Es necesario modificar la clase WebSecurityConfig para poder agregar la url /signup 
y no la tome Spring Security como una URL donde el usuario debe estar autenticado

``` java

     
      http
         .authorizeRequests()
            .antMatchers("/", "/index","/signup").permitAll()
            
```

El código completo de la clase com.neosuniversity.videolibrary.security.WebSecurityConfig es el siguiente

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
            .antMatchers("/", "/index", "/signup").permitAll()
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


Es necesario modificar la pagina login.jsp para poder invocar el controller y poder dar de alta un nuevo usuario en el sistema

``` html

<div id="formFooter">
  <a class="underlineHover" href="/signup">No estás registrado?</a>
</div>
  
```

El código completo de login.jsp es :

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
              <a class="underlineHover" href="/signup">No estás registrado?</a>
            </div>

		</div>
	</div>


</body>
</html>

```

Ahora son necesarias dos paginas una para realizar la captura de la información del usuario 

webapp/WEB_INF/jsp/signup.jsp

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


```

Y por último agregar una nueva página donde se muestra un mensaje que el alta del usuario fue exitosa

webapp/WEB_INF/jsp/successSignup.jsp

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
   
   <div class="alert alert-success" role="alert">
  El usuario se dió de alta exitosamente,  <a href="/login">Ingresar ahora</a>
</div>

	</div>

<jsp:include page="includes/jsscripts.jsp"></jsp:include>
</body>
</html>


``` 