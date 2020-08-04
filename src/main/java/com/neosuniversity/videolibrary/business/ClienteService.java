package com.neosuniversity.videolibrary.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.neosuniversity.videolibrary.services.Notificacion;

@Service
public class ClienteService {
	
	@Autowired
	@Qualifier("whatsapp")
	private Notificacion notifica;
	
	public void registraCliente( ) {
		
		System.out.println("Aqui van los pasos para registrar a un cliente");
		
		notifica.envia("CLiente registrado exitosamente");
		
	
	}
	

}
