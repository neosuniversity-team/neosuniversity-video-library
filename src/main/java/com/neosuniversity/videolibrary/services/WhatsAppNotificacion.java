package com.neosuniversity.videolibrary.services;


import org.springframework.stereotype.Service;

@Service ("whatsapp")
public class WhatsAppNotificacion implements Notificacion{

	@Override
	public void envia(String notificacion) {
		System.out.println("Notificacion usando whatsapp : " + notificacion);
		
	}

}
