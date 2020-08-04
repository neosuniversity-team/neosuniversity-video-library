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
