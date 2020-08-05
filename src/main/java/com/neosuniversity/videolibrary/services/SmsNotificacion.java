package com.neosuniversity.videolibrary.services;

import org.springframework.stereotype.Service;

@Service("SmsNotificacion")
public class SmsNotificacion implements Notificacion {

	@Override
	public void envia(String notificacion) {
		System.out.println("Enviando una notificacion por SMS : " + notificacion);

	}

}
