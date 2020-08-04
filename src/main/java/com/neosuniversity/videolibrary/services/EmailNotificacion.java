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
