package com.djcodes.spring.rest.errorhandling.exceptions;

public enum ErrorCodes {

	VALIDATION_PARSE_ERROR(422, "Parsing Error");

	private int code;
	private String message;

	ErrorCodes(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
