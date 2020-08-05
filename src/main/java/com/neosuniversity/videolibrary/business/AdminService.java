package com.neosuniversity.videolibrary.business;

import org.springframework.beans.factory.annotation.Autowired;

import com.neosuniversity.videolibrary.services.Notificacion;

public class AdminService {

	@Autowired
	private Notificacion notificacion;
	
	
	public void defineMetrica() {
		notificacion.envia("visualizando metrica");
	}
	
}
