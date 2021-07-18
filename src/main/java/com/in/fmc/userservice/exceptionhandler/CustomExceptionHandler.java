package com.in.fmc.userservice.exceptionhandler;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.in.fmc.userservice.constants.ExceptionConstants;
import com.in.fmc.userservice.exceptions.InvalidCredentialsException;
import com.in.fmc.userservice.exceptions.UsernameOccupiedException;
import com.in.fmc.userservice.models.ErrorResource;
import com.in.fmc.userservice.models.ErrorResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		// TODO Auto-generated method stub

		log.error("Exception occured - MethodArgumentNotValidException: {}", ex);

		Set<String> errors = ex.getBindingResult().getFieldErrors().stream().map(error -> error.getDefaultMessage())
				.collect(Collectors.toSet());

		errors.addAll(ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
				.collect(Collectors.toSet()));

		String errorString = errors.stream().sorted().collect(Collectors.joining(", "));

		return createErrorResponse(ExceptionConstants.VALIDATION_ERROR_MESSAGE + errorString, status, request);

	}

	@ExceptionHandler(InvalidCredentialsException.class)
	private ResponseEntity<Object> handleInvalidCredentialsException(InvalidCredentialsException icex,
			WebRequest webRequest) {

		log.error("Exception occured - InvalidCredentialsException: {}", icex);
		return createErrorResponse(icex.getMessage(), HttpStatus.UNAUTHORIZED, webRequest);

	}

	@ExceptionHandler(UsernameOccupiedException.class)
	private ResponseEntity<Object> handleUsernameOccupiedException(UsernameOccupiedException uoex,
			WebRequest webRequest) {
		log.error("Exception occured - UsernameOccupiedException: {}", uoex);

		return createErrorResponse(uoex.getMessage(), HttpStatus.NOT_ACCEPTABLE, webRequest);
	}

	private static ResponseEntity<Object> createErrorResponse(String errmsg, HttpStatus httpStatus,
			WebRequest webRequest) {

		String title = httpStatus.name();
		Integer status = httpStatus.value();

		String uri = ((ServletWebRequest) webRequest).getRequest().getRequestURI();
		String type = ExceptionConstants.RESPONSE_TYPE_DEFINITION;

		ErrorResource errorResource = new ErrorResource(type, title, status, errmsg, uri);

		ErrorResponse errorResponse = new ErrorResponse(errorResource);

		return new ResponseEntity<>(errorResponse, httpStatus);

	}
}
