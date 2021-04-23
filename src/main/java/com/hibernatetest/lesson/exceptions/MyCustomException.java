package com.hibernatetest.lesson.exceptions;

public class MyCustomException extends RuntimeException {

    public MyCustomException() {
    }

    public MyCustomException(String message) {
        super(message);
    }
}
