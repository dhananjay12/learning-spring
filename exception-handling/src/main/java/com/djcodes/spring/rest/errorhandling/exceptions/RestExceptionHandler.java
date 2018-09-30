package com.djcodes.spring.rest.errorhandling.exceptions;

import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseBody
	@ExceptionHandler(value = ValidationException.class)
	public ResponseEntity<?> handleException(ValidationException exception) {
		return ResponseEntity.status(exception.getErrorCode()).build();
	}

	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<VndErrors> employeeNotFoundException(EmployeeNotFoundException e) {
		return errorResponseEntity(e, HttpStatus.NOT_FOUND, e.getEmployeeId() + "");
	}

	protected <E extends Exception> ResponseEntity<VndErrors> errorResponseEntity(E e, HttpStatus httpstatus,
			String logref) {
		MediaType mediaType = MediaType.parseMediaType("application/vnd.errors");
		String msg = StringUtils.hasText(e.getMessage()) ? e.getMessage() : e.getClass().getSimpleName();
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(mediaType);
		return new ResponseEntity<VndErrors>(new VndErrors(logref, msg), httpHeaders, httpstatus);
	}

	private ResponseEntity<Object> errorResponse(HttpStatus status, HttpHeaders headers, String message) {
		return ResponseEntity.status(status).headers(headers).build();
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String message = ex.getBindingResult().getFieldErrors().stream().map((fieldError) -> String
				.format("Error found for parameter '%s'; %s", fieldError.getField(), fieldError.getDefaultMessage()))
				.findFirst().orElse(ex.getMessage());
		return errorResponse(HttpStatus.BAD_REQUEST, new HttpHeaders(), message);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		if ("com.fasterxml.jackson.databind.exc.InvalidFormatException".equals(ex.getCause().getClass().getName())) {
			return errorResponse(HttpStatus.UNPROCESSABLE_ENTITY, new HttpHeaders(), "Invalid field format");
		}
		return errorResponse(HttpStatus.BAD_REQUEST, new HttpHeaders(), "Invalid Json format");
	}

}
