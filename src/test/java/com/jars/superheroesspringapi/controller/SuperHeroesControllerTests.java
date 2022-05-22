package com.jars.superheroesspringapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SuperHeroesController.class)
public class SuperHeroesControllerTests {

    @Autowired
    private MockMvc mockMvc;

    private final String API_PATH = "/api/superheroes";

    @Test
    void getAllSuperHeroesExpectSuperHeroesListResponseOK() throws Exception {
        //Assert
        mockMvc.perform(get(API_PATH).contentType("application/json")).andExpect(status().isOk());
    }

}
