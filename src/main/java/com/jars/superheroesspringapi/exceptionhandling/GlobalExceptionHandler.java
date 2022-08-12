package com.jars.superheroesspringapi.exceptionhandling;

import com.jars.superheroesspringapi.exceptionhandling.exceptions.NotSuperHeroFoundException;
import com.jars.superheroesspringapi.exceptionhandling.exceptions.NotSuperHeroesException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotSuperHeroesException.class)
    public ResponseEntity<Object> handleGeneralException(NotSuperHeroesException e, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        List<ErrorItem> errorItems = Stream.of(new ErrorItem("No Super Heroes found", "404",
                e.getMessage())).collect(Collectors.toList());
        return handleExceptionInternal(e, errorItems, headers, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(NotSuperHeroFoundException.class)
    public ResponseEntity<Object> handleGeneralException(NotSuperHeroFoundException e, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        List<ErrorItem> errorItems = Stream.of(new ErrorItem("No Super Hero ID found", "404",
                e.getMessage())).collect(Collectors.toList());
        return handleExceptionInternal(e, errorItems, headers, HttpStatus.NOT_FOUND, request);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleGeneralException(RuntimeException e, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();
        List<ErrorItem> errorItems = Stream.of(new ErrorItem("Runtime Exception", "GL000",
                e.getMessage())).collect(Collectors.toList());
        return handleExceptionInternal(e, errorItems, headers, HttpStatus.BAD_REQUEST, request);
    }

}
