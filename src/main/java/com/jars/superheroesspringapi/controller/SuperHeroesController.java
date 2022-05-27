package com.jars.superheroesspringapi.controller;


import com.jars.superheroesspringapi.dto.SuperHeroDto;
import com.jars.superheroesspringapi.entity.SuperHero;
import com.jars.superheroesspringapi.service.SuperHeroesService;
import io.swagger.annotations.*;
import org.modelmapper.ModelMapper;
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


    @Autowired
    private ModelMapper modelMapper;


    @GetMapping
    @ApiOperation(value = "Get the list of all the super heroes.", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "No super heroes found"),
    })
    public ResponseEntity<List<SuperHero>> getAllSuperHeroes() {
        return ok(superHeroesService.getAllSuperHeroes());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get super hero by id.", response = SuperHero.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "No super hero found by ID"),
    })
    @ApiParam(value = "Super Hero ID", required = true)
    public ResponseEntity<SuperHero> getSuperHeroById(@PathVariable("id") long id) {
        return ok(superHeroesService.getSuperHeroByID(id));
    }

    @GetMapping("/findByContainsName/{name}")
    @ApiOperation(value = "Get super hero by contains name", response = SuperHero.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "No super heroes found with that part of the name"),
    })
    @ApiParam(value = "Super Hero part of the name", required = true)
    public ResponseEntity<List<SuperHero>> getSuperHeroesByContainsName(@PathVariable("name") String name) {
        return ok(superHeroesService.getSuperHeroByPartOfName(name));
    }

    @PutMapping
    @ApiOperation(value = "Modify super hero", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "No super hero found with that ID"),
    })
    public ResponseEntity<SuperHeroDto> updateSuperHeroById(@RequestBody SuperHeroDto superHeroToModifyDto) {
        SuperHero superHeroToModify = modelMapper.map(superHeroToModifyDto, SuperHero.class);
        return ok(superHeroesService.updateSuperHero(superHeroToModify));
    }

    @DeleteMapping
    @ApiOperation(value = "Delete super hero by id", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "No super hero found with that ID"),
    })
    public ResponseEntity<Long> deleteSuperHeroById(@RequestBody SuperHeroDto superHeroDto) {
        SuperHero superHero = modelMapper.map(superHeroDto, SuperHero.class);
        superHeroesService.deleteSuperHero(superHero);
        return new ResponseEntity<>(superHero.getId(), HttpStatus.NO_CONTENT);
    }
}
