package com.hibernatetest.lesson.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(MyCustomException.class)
    public ResponseEntity<ErrorMessage> getNotFoundException(MyCustomException msg) {
        ErrorMessage error = new ErrorMessage();
        error.setHttpStatus(HttpStatus.NOT_FOUND.value());
        error.setErrorTime(LocalDateTime.now());
        error.setErrorMessage(msg.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
