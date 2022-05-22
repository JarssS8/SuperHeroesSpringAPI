package com.jars.superheroesspringapi.controller;


import com.jars.superheroesspringapi.entity.SuperHero;
import com.jars.superheroesspringapi.service.SuperHeroesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/superheroes")
public class SuperHeroesController {

    @Autowired
    SuperHeroesService superHeroesService;

    @GetMapping
    private ResponseEntity<List<SuperHero>> getAllSuperHeroes() {
        return ok(superHeroesService.getAllSuperHeroes());
    }
}
