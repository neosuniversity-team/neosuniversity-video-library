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


## Agregue un directorio llamado webapp en src/main/