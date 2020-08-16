# Configuración de Spring MVC

Esta práctica está enfocada a la configuración de Spring MVC usando Boot

### Es necesario agregar las dependencias en el pom.xml para ocupar JSP
### pom.xml
``` xml

	<dependencies>
.......
.......

		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>jstl</artifactId>
		</dependency>
		<dependency>
			<groupId>org.apache.tomcat.embed</groupId>
			<artifactId>tomcat-embed-jasper</artifactId>
			<scope>provided</scope>
		</dependency> 

.......
.......
</dependencies>		
		
``` 

### En el archivo application.properties es necesario agregar al final del archivo la siguiente configuración
### application.properties 
``` js

spring.mvc.view.prefix=/WEB-INF/jsp/
spring.mvc.view.suffix=.jsp

```

### Controller
### com.neosuniversity.videolibrary.controllers.MainController

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

}


```


### Agregue los directorios siguientes  en src/main/webapp/WEB-INF/jsp
### En este directorio se agregarán los archivos JSP

### login.jsp

``` html
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>Document</title>
</head>
<body>
	
	Hola Mundo JSP
	
</body>
</html>
```

### Con el fin de no agregar información al arrancar el proyecto es necesario cambiar la programacion de la clase VideoLibraryApplication
### com.neosuniversity.videolibrary.VideoLibraryApplication
``` java
package com.neosuniversity.videolibrary;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class VideoLibraryApplication  {
	
	public static void main(String[] args) {
		SpringApplication.run(VideoLibraryApplication.class, args);
	}


}

``` 

