package com.jars.superheroesspringapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jars.superheroesspringapi.entity.SuperHero;
import com.jars.superheroesspringapi.service.SuperHeroesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SuperHeroesController.class)
public class SuperHeroesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SuperHeroesService superHeroService;

    private final String API_PATH = "/api/superheroes";

    List<SuperHero> superHeroesList;

    @BeforeEach
    public void setup() {
        superHeroesList = new ArrayList<>();
        superHeroesList.add(new SuperHero(1, "Superman"));
        superHeroesList.add(new SuperHero(2, "Batman"));
        superHeroesList.add(new SuperHero(3, "Spiderman"));
        superHeroesList.add(new SuperHero(4, "Manolito El Fuerte"));
        superHeroesList.add(new SuperHero(5, "El flautista"));
        superHeroesList.add(new SuperHero(6, "Robert Widow"));
    }


    @Test
    void getAllSuperHeroes_Expect200Code_ResponseOK() throws Exception {
        // Act
        when(superHeroService.getAllSuperHeroes()).thenReturn(superHeroesList);

        //Assert
        mockMvc.perform(get(API_PATH).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
    }

    @Test
    void getAllSuperHeroes_ExpectSuperHeroesList_ResponseOK() throws Exception {
        // Act
        when(superHeroService.getAllSuperHeroes()).thenReturn(superHeroesList);

        //Assert
        mockMvc.perform(get(API_PATH).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(superHeroesList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].name").isNotEmpty());
    }

    @Test
    void getSuperHeroByID_Expect200Code_ResponseOK() throws Exception {
        SuperHero superHero = superHeroesList.get(0);

        // Act
        when(superHeroService.getSuperHeroByID(superHero.getId())).thenReturn(superHero);

        //Assert
        mockMvc.perform(get(String.format("%s/%d", API_PATH, superHero.getId())).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
    }

    @Test
    void getSuperHeroById_ExpectSuperHero_ResponseOK() throws Exception {
        SuperHero superHero = superHeroesList.get(0);

        // Act
        when(superHeroService.getSuperHeroByID(superHero.getId())).thenReturn(superHero);

        //Assert
        mockMvc.perform(get(String.format("%s/%d", API_PATH, superHero.getId())).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(superHero.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("name").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(superHero.getName()));
    }

    @Test
    void getSuperHeroesByPartOfName_Expect200Code_ResponseOK() throws Exception {
        List<SuperHero> resultSuperHeroesList = superHeroesList.stream().filter(superHero -> superHero.getName()
                .toLowerCase().contains("man")).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        String searchName = "man";

        // Act
        when(superHeroService.getSuperHeroByPartOfName(searchName)).thenReturn(resultSuperHeroesList);

        //Assert
        String path = String.format("%s/findByContainsName/%s", API_PATH, searchName);
        mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk());
    }

    @Test
    void getSuperHeroesByPartOfName_ExpectSuperHeroes_ResponseOK() throws Exception {
        List<SuperHero> resultSuperHeroesList = superHeroesList.stream().filter(superHero -> superHero.getName()
                .toLowerCase().contains("man")).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        String searchName = "man";

        // Act
        when(superHeroService.getSuperHeroByPartOfName(searchName)).thenReturn(resultSuperHeroesList);

        //Assert
        String path = String.format("%s/findByContainsName/%s", API_PATH, searchName);
        mockMvc.perform(get(path).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(resultSuperHeroesList.size()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].name").isNotEmpty());
    }

    @Test
    void updateSuperHero_Expect200Code_ResponseOK() throws Exception {
        SuperHero superHero = superHeroesList.get(0);
        String newName = "San Juan";

        // Act
        superHero.setName(newName);
        ObjectMapper objectMapper = new ObjectMapper();
        String bodyJson = objectMapper.writeValueAsString(superHero);
        when(superHeroService.updateSuperHero(superHero)).thenReturn(superHero);

        //Assert
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put(API_PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(bodyJson);

        mockMvc.perform(builder).andExpect(status().isOk());
    }

    @Test
    void updateSuperHero_ExpectSuperHeroModified_ResponseOK() throws Exception {
        SuperHero superHero = superHeroesList.get(0);
        String newName = "San Juan";

        // Act
        superHero.setName(newName);
        ObjectMapper objectMapper = new ObjectMapper();
        String bodyJson = objectMapper.writeValueAsString(superHero);
        when(superHeroService.updateSuperHero(superHero)).thenReturn(superHero);

        //Assert
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.put(API_PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(bodyJson);

        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(superHero.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(newName));
    }

    @Test
    void deleteSuperHeroById_Expect204Code_ResponseNoContent() throws Exception {
        SuperHero superHero = superHeroesList.get(0);
        ObjectMapper objectMapper = new ObjectMapper();
        String bodyJson = objectMapper.writeValueAsString(superHero);

        //Assert
        MockHttpServletRequestBuilder builder =
                MockMvcRequestBuilders.delete(API_PATH)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(bodyJson);

        mockMvc.perform(builder)
                .andExpect(status().isNoContent());
    }
}
