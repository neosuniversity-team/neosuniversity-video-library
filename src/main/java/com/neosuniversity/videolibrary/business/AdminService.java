package com.neosuniversity.videolibrary.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.neosuniversity.videolibrary.services.Notificacion;

@Service
public class AdminService {

	@Autowired
	@Qualifier("SmsNotificacion")
	private Notificacion notificacion;
	
	
	public void defineMetrica() {
		notificacion.envia("visualizando metrica");
	}
	
}
