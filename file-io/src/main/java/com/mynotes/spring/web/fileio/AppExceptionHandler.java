package com.mynotes.spring.web.fileio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = FileStorageException.class)
    public ResponseEntity<?> handleException(FileStorageException exception) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(exception.getMsg());
    }
}
