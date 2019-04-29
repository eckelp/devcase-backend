package com.vsm.devcase.exceptions.handler;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ApiError {
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime timestamp;
	
	private String message;
	
	private String exception;

	public ApiError(String message, String exception) {
		super();
		
		this.setTimestampNow();
		this.message = message;
		this.exception = exception;
	}

	public LocalDateTime getTimestamp() {
		this.setTimestampNow();
		
		return this.timestamp;
	}
	
	private void setTimestampNow() {
		this.timestamp = LocalDateTime.now();
	}

	
	public String getMessage() {
		return message;
	}

	
	public String getException() {
		return exception;
	}

	
	
	
	
	
	

}
