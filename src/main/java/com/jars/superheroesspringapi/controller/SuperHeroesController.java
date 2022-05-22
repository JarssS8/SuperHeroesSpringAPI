package com.jars.superheroesspringapi.controller;


import com.jars.superheroesspringapi.entity.SuperHero;
import com.jars.superheroesspringapi.service.SuperHeroesService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/superheroes")
@Api(value = "Super Heroes API")
public class SuperHeroesController {

    @Autowired
    SuperHeroesService superHeroesService;


    @GetMapping
    @ApiOperation(value = "Get all super heroes", response = List.class)
    private ResponseEntity<List<SuperHero>> getAllSuperHeroes() {
        return ok(superHeroesService.getAllSuperHeroes());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get super hero by id", response = SuperHero.class)
    private ResponseEntity<SuperHero> getSuperHeroById(@PathVariable("id") long id) {
        return ok(superHeroesService.getSuperHeroByID(id));
    }
}
