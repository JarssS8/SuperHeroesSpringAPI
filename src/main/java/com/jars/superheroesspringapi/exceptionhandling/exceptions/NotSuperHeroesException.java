package com.jars.superheroesspringapi.exceptionhandling.exceptions;

public class NotSuperHeroesException extends RuntimeException {

    private static final String NOT_SUPERHEROES_EXCEPTION_MESSAGE = "There are not super heroes in the database";

    public NotSuperHeroesException() {
        super(NOT_SUPERHEROES_EXCEPTION_MESSAGE);
    }

}
