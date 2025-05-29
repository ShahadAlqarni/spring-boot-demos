package com.example.springRest.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {

    //Add an exception handler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exe) {
        //create a StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();

        error.setErrorCode(HttpStatus.NOT_FOUND.value());
        error.setErrorMessage(exe.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // catch any other error
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception ex) {

        //create a StudentErrorResponse
        StudentErrorResponse error = new StudentErrorResponse();

        error.setErrorCode(HttpStatus.BAD_REQUEST.value());
        error.setErrorMessage("Bad request");
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
