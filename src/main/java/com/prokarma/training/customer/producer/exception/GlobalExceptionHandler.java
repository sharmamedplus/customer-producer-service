package com.prokarma.training.customer.producer.exception;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prokarma.training.customer.producer.domain.ErrorResponse;
import com.prokarma.training.customer.producer.util.ObjectMapperUtil;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	public static final String ERROR_STATUS = "error";

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorResponse> handleException(NoHandlerFoundException ex, HttpServletRequest request) {
		ErrorResponse errorResponse = prepareErrorResponse(ex);
		LOG.error("NoHandlerFoundException : {}", errorResponse);
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({ ResourceAccessException.class, ApplicationRuntimeException.class })
	public final ResponseEntity<ErrorResponse> handleException(ResourceAccessException ex, HttpServletRequest request) {
		ErrorResponse errorResponse = prepareErrorResponse(ex);
		LOG.error("ApplicationRuntimeException : {}", errorResponse);
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler({ HttpMessageNotReadableException.class, MissingRequestHeaderException.class })
	public final ResponseEntity<ErrorResponse> handleException(Exception ex, HttpServletRequest request) {
		ErrorResponse errorResponse = prepareErrorResponse(ex);
		LOG.error("MissingRequestHeaderException : {}", errorResponse);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RequestValidationException.class)
	public final ResponseEntity<ErrorResponse> handleException(RequestValidationException ex,
			HttpServletRequest request) {
		ErrorResponse errorResponse = prepareErrorResponse(ex);
		LOG.error("RequestValidationException : {}", errorResponse);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException ex,
			HttpServletRequest request) {
		ErrorResponse errorResponse = prepareErrorResponse(ex);
		LOG.error("MethodArgumentNotValidException : {}", errorResponse);
		return new ResponseEntity<>(processFieldErrors(ex.getBindingResult().getFieldErrors(), errorResponse), HttpStatus.BAD_REQUEST);
	}

	private ErrorResponse processFieldErrors(List<FieldError> fieldErrors,
			ErrorResponse errorResponse) {

		Map<String, TreeSet<String>> fieldValidationError = new TreeMap<>();
		
		for (FieldError fieldError : fieldErrors) {
			TreeSet<String> error = new TreeSet<>();
			if (fieldValidationError.containsKey(fieldError.getField())) {
				error = fieldValidationError.get(fieldError.getField());
			}
			error.add(fieldError.getDefaultMessage());
			fieldValidationError.put(fieldError.getField(), error);
		}
		errorResponse.setStatus(ERROR_STATUS);
		FieldErrorResponse fieldErrorResponse = new FieldErrorResponse();
		fieldErrorResponse.setModelState(fieldValidationError);
		errorResponse.setMessage(ObjectMapperUtil.returnJsonFromObject(fieldErrorResponse));

		return errorResponse;
	}
	
	private ErrorResponse prepareErrorResponse(Exception ex) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setStatus(ERROR_STATUS);
		errorResponse.setMessage(ex.getLocalizedMessage());
		errorResponse.setErrorType(ex.getClass().getSimpleName());
		return errorResponse;
	}
}
