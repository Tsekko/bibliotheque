package com.mk.bibliotheque.controllers;

import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mk.bibliotheque.exceptions.ClosedException;
import com.mk.bibliotheque.exceptions.InvalidFormException;
import com.mk.bibliotheque.models.ApiError;
import com.mk.bibliotheque.security.jwt.AuthEntryPointJwt;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);
	
	private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}
	
	@ExceptionHandler(InvalidFormException.class)
	protected ResponseEntity<Object> handleInvalidForm(InvalidFormException ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	protected ResponseEntity<Object> handleNoSuchElement(NoSuchElementException ex) {
		ApiError apiError = new ApiError(HttpStatus.NOT_FOUND);
		apiError.setMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	protected ResponseEntity<Object> handleAccessDenied(AccessDeniedException ex) {
		ApiError apiError = new ApiError(HttpStatus.FORBIDDEN);
		apiError.setMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(ClosedException.class)
	protected ResponseEntity<Object> handleClosed(ClosedException ex) {
		ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST);
		apiError.setMessage(ex.getMessage());
		return buildResponseEntity(apiError);
	}
	
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleException(Exception ex) {
		logger.error(ex.getMessage());
		ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR);
		apiError.setMessage("Unknown error");
		return buildResponseEntity(apiError);
	}
}
