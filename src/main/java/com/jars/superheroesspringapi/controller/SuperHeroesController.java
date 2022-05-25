package com.jars.superheroesspringapi.controller;


import com.jars.superheroesspringapi.entity.SuperHero;
import com.jars.superheroesspringapi.service.SuperHeroesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/findByContainsName/{name}")
    @ApiOperation(value = "Get super hero by contains name", response = SuperHero.class)
    private ResponseEntity<List<SuperHero>> getSuperHeroesByContainsName(@PathVariable("name") String name) {
        return ok(superHeroesService.getSuperHeroByPartOfName(name));
    }

    @PutMapping
    @ApiOperation(value = "Modify super hero", response = ResponseEntity.class)
    private ResponseEntity<SuperHero> updateSuperHeroById(@RequestBody SuperHero superHeroToModify) {
        return ok(superHeroesService.updateSuperHero(superHeroToModify));
    }

    @DeleteMapping
    @ApiOperation(value = "Delete super hero by id", response = ResponseEntity.class)
    private ResponseEntity<Long> deleteSuperHeroById(@RequestBody SuperHero superHero) {
        superHeroesService.deleteSuperHero(superHero);
        return new ResponseEntity<>(superHero.getId(), HttpStatus.NO_CONTENT);
    }
}
