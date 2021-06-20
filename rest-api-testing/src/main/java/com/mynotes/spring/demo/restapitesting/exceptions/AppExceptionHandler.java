package com.mynotes.spring.demo.restapitesting.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = RestTemplateCustomException.class)
    public ResponseEntity<?> handleException(RestTemplateCustomException exception) {
        return ResponseEntity.status(exception.getStatus()).body(exception.getMsg());
    }

}
