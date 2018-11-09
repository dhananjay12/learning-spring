package com.djcodes.spring.exceptionhandling.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "User Not found")
public class UserNotFoundException extends RuntimeException {

}
