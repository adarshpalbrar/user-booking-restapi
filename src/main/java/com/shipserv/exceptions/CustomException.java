package com.shipserv.exceptions;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

@ControllerAdvice
@RestController
public class CustomException extends ResponseEntityExceptionHandler {


    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleCustomExceptions(Exception ex, WebRequest request) {
        final ExceptionResponse.ExceptionResponseBuilder builder = ExceptionResponse.builder();
        final ExceptionResponse exceptionResponse = builder.localDateTime(LocalDateTime.now())
                .message(ex.getMessage())
                .details(request.getDescription(false)).build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleExceptions(Exception ex, WebRequest request) {
        final ExceptionResponse.ExceptionResponseBuilder builder = ExceptionResponse.builder();
        final ExceptionResponse exceptionResponse = builder.localDateTime(LocalDateTime.now())
                .message(ex.getMessage())
                .details(request.getDescription(false)).build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        StringBuilder errorMessage = new StringBuilder();
        final List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
        for (ObjectError allError : allErrors) {
            errorMessage.append(allError.getDefaultMessage());
        }
        final ExceptionResponse.ExceptionResponseBuilder builder = ExceptionResponse.builder();
        final ExceptionResponse exceptionResponse = builder.localDateTime(LocalDateTime.now())
                .message("Failed Validation")
                .details(errorMessage.toString()).build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
