## Spring Core Práctica 1

En esta práctica aplicará los conceptos básicos de dependency Injection como lo son el uso de las anotaciones

* @Service
* @Autowired
* @Primary
* @Qualifier


### Listado de archivos:
### com.neosuniversity.videolibrary.services.Notificacion
```
package com.neosuniversity.videolibrary.services;

public interface Notificacion {
	
	public void envia(String notificacion);

}
```

### com.neosuniversity.videolibrary.services.SmsNotificacion
```
package com.neosuniversity.videolibrary.services;

import org.springframework.stereotype.Service;

@Service
public class SmsNotificacion implements Notificacion {

	@Override
	public void envia(String notificacion) {
		System.out.println("Enviando una notificacion por SMS : " + notificacion);

	}

}
```

### com.neosuniversity.videolibrary.services.EmailNotificacion
```
package com.neosuniversity.videolibrary.services;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class EmailNotificacion implements Notificacion {
	

	@Override
	public void envia(String notificacion) {
		System.out.println("Implementacion de notificacion por medio de mail: " + notificacion);
		
	}

}
```

### com.neosuniversity.videolibrary.services.WhatsAppNotificacion
```
package com.neosuniversity.videolibrary.services;


import org.springframework.stereotype.Service;

@Service ("whatsapp")
public class WhatsAppNotificacion implements Notificacion{

	@Override
	public void envia(String notificacion) {
		System.out.println("Notificacion usando whatsapp : " + notificacion);
		
	}

}
```

### Creación de la clase de Negocio
### com.neosuniversity.videolibrary.business.ClienteService
```
package com.neosuniversity.videolibrary.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.neosuniversity.videolibrary.services.Notificacion;

@Service
public class ClienteService {
	
	@Autowired
	//@Qualifier("whatsapp") des- comentar esta linea para revisar el funcionamiento de Qualifier
	private Notificacion notifica;
	
	public void registraCliente( ) {
		
		System.out.println("Aqui van los pasos para registrar a un cliente");
		
		notifica.envia("CLiente registrado exitosamente");
		
	
	}
	

}
```


### Para poder ver el demo en es necesario hacer un cambio en la clase principal
### com.neosuniversity.videolibrary.VideoLibraryApplication

```
package com.neosuniversity.videolibrary;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.neosuniversity.videolibrary.business.ClienteService;

@SpringBootApplication
public class VideoLibraryApplication {

	public static void main(String[] args) {
	ApplicationContext ctx= (ApplicationContext)SpringApplication.run(VideoLibraryApplication.class, args);
	
	ClienteService service= ctx.getBean(ClienteService.class);
	
	service.registraCliente();
	}

}

```
