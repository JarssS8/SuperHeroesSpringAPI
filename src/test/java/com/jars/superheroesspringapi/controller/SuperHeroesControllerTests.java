package com.jars.superheroesspringapi.controller;

import com.jars.superheroesspringapi.entity.SuperHero;
import com.jars.superheroesspringapi.service.SuperHeroesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SuperHeroesController.class)
public class SuperHeroesControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SuperHeroesService superHeroService;

    private final String API_PATH = "/api/superheroes";

    @Test
    void getAllSuperHeroesExpectSuperHeroesListResponseOK() throws Exception {
        //Arrange
        List<SuperHero> superHeroesList = new ArrayList<>();
        superHeroesList.add(new SuperHero("Superman"));
        superHeroesList.add(new SuperHero("Batman"));
        superHeroesList.add(new SuperHero("Spiderman"));

        // Act
        when(superHeroService.getAllSuperHeroes()).thenReturn(superHeroesList);

        //Assert
        mockMvc.perform(get(API_PATH).contentType("application/json")).andExpect(status().isOk());

    }

}
