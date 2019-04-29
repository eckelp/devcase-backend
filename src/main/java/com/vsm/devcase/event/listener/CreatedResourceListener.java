package com.vsm.devcase.event.listener;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vsm.devcase.event.CreatedResourceEvent;

@Component
public class CreatedResourceListener implements ApplicationListener<CreatedResourceEvent>{

	
	
	@Override
	public void onApplicationEvent(CreatedResourceEvent event) {
		
		
		
		HttpServletResponse response = event.getResponse();
		
		Integer id = event.getResourceId();
		
		
		this.defineHeaderLocation(response, id);
		
	}

	private void defineHeaderLocation(HttpServletResponse response, Integer id) {
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(id).toUri();
		
		
		response.setHeader("Location", uri.toASCIIString());
	}
	
	

}
