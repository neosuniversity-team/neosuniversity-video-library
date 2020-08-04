## Spring Core Práctica 1

En esta práctica aplicará los conceptos básicos de dependency Injection como lo son el uso de las anotaciones

* @Service
* @Autowired
* @Primary
* @Qualifier


### Cambios a realizar:
### com.neosuniversity.videolibrary.services.Notificacion
```
package com.neosuniversity.videolibrary.services;

public interface Notificacion {
	
	public void envia(String notificacion);

}
```
