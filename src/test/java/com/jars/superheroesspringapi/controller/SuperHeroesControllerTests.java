package com.jars.superheroesspringapi.controller;

import com.jars.superheroesspringapi.entity.SuperHero;
import com.jars.superheroesspringapi.service.SuperHeroesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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

    List<SuperHero> superHeroesList;

    @BeforeEach
    public void setup() {
        superHeroesList = new ArrayList<>();
        superHeroesList.add(new SuperHero(1,"Superman"));
        superHeroesList.add(new SuperHero(2,"Batman"));
        superHeroesList.add(new SuperHero(3,"Spiderman"));
        superHeroesList.add(new SuperHero(4,"Manolito El Fuerte"));
    }


    @Test
    void getAllSuperHeroesExpect200CodeResponseOK() throws Exception {
        // Act
        when(superHeroService.getAllSuperHeroes()).thenReturn(superHeroesList);

        //Assert
        mockMvc.perform(get(API_PATH).contentType("application/json")).andExpect(status().isOk());
    }

    @Test
    void getAllSuperHeroesExpectSuperHeroesListResponseOK() throws Exception {
        // Act
        when(superHeroService.getAllSuperHeroes()).thenReturn(superHeroesList);

        //Assert
        mockMvc.perform(get(API_PATH).contentType("application/json")).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(superHeroesList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].name").isNotEmpty());
    }
}
