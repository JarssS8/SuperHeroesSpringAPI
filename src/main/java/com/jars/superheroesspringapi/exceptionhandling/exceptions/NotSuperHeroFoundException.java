package com.jars.superheroesspringapi.exceptionhandling.exceptions;

public class NotSuperHeroFoundException extends RuntimeException {

    private static final String NOT_SUPERHERO_FOUND_EXCEPTION_MESSAGE = "There are not super hero with that ID";

    public NotSuperHeroFoundException() {
        super(NOT_SUPERHERO_FOUND_EXCEPTION_MESSAGE);
    }

}
