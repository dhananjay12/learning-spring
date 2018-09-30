package com.djcodes.spring.rest.errorhandling.exceptions;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private Integer errorCode;

	public ValidationException(ErrorCodes errorCode) {
		this.errorCode = errorCode.getCode();

	}

	public Integer getErrorCode() {
		return errorCode;
	}
}
