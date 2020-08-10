# Configuración de Spring MVC

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