# Spring MVC -  Envío de información de formularios al controller

El objetivo de la práctica es poder enviar datos de la capa de vista al controller en específico un login básico

### Lista de archivos
### login.jsp

``` html
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>Demo</title>
</head>
<body>

<form action="/autentica" method="post">

   Usuario: 
   <input type="text" name="usuario" >
  Password :
  <input type="password" name="password"> 
  
  <input type="submit" value="Enviar">

</form>
	
</body>
</html>

```

### com.neosuniversity.videolibrary.controllers.Autentica

``` java
package com.neosuniversity.videolibrary.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Autentica {

	@RequestMapping(value="/autentica" , method = RequestMethod.POST)
	public String autentica (@RequestParam String usuario, @RequestParam String password) {
		System.out.println(usuario);
		System.out.println(password);
		return "home";
	}
}

```


