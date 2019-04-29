package com.vsm.devcase.exceptions.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.vsm.devcase.exceptions.ProdutoSemEstoqueException;
import com.vsm.devcase.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ApiExceptionHanlder extends ResponseEntityExceptionHandler{

	
	@Autowired
	private MessageSource messageSource;
	
	
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ApiError apiError = new ApiError(this.message("field.notFound"), ex.getCause() != null ? ex.getCause().toString() : ex.toString());
		
		
		return handleExceptionInternal(ex, apiError,headers, status, request);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
	
		List<ApiError> list = new ArrayList<ApiError>();
		
		List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
		
		fieldErrors.forEach((field) -> {list.add(new ApiError(message(field), ex.toString())); });
				
		return handleExceptionInternal(ex, list, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
		
		ApiError apiError = new ApiError(this.message("resource.notFound"), ex.toString());
		
		return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
		
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request){
		
		ApiError apiError = new ApiError(this.message("resource.operationNotAllowed"), ExceptionUtils.getRootCauseMessage(ex));
		
		return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
		
	}
	
	@ExceptionHandler(ProdutoSemEstoqueException.class)
	public ResponseEntity<Object> handleResourceNotFoundException(ProdutoSemEstoqueException ex, WebRequest request){
		
		ApiError apiError = new ApiError(this.message("produto.semEstoque"), ex.toString());
		
		return handleExceptionInternal(ex, apiError, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
		
	}
	
	
	private String message(MessageSourceResolvable fieldError) {
		return messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
	}
	
	private String message(String stringCode) {
		
		return messageSource.getMessage(stringCode, null, LocaleContextHolder.getLocale());
		
	}
	
}
