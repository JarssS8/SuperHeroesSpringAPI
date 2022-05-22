package com.jars.superheroesspringapi.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/superheroes")
public class SuperHeroesController {

    @GetMapping
    private HttpStatus getAllSuperHeroes() {
        return HttpStatus.OK;
    }
}
