package com.vsm.devcase.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

public class CreatedResourceEvent extends ApplicationEvent{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4658570395190700495L;
	
	
	private HttpServletResponse response;
	private Integer resourceId;

	public CreatedResourceEvent(Object source, HttpServletResponse response, Integer resourceId) {
		super(source);
		
		this.response = response;
		this.resourceId = resourceId;

	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public Integer getResourceId() {
		return resourceId;
	}

	
	
	

}
