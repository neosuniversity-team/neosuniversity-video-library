package com.neosuniversity.videolibrary;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.neosuniversity.videolibrary.business.AdminService;

@SpringBootApplication
public class VideoLibraryApplication {

	public static void main(String[] args) {
	ApplicationContext ctx= (ApplicationContext)SpringApplication.run(VideoLibraryApplication.class, args);
	
	AdminService service = ctx.getBean(AdminService.class);
	service.defineMetrica();
	}

}
