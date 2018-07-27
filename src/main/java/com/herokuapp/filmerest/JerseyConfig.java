package com.herokuapp.filmerest;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.herokuapp.filmerest.controller.FilmeController;

@Component
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
//		packages("com.herokuapp.filmerest.controller")
		register(FilmeController.class);
		register(CorsFilter.class);
	}

}