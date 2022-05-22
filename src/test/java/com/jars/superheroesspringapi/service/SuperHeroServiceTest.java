package com.jars.superheroesspringapi.service;

import com.jars.superheroesspringapi.entity.SuperHero;
import com.jars.superheroesspringapi.exceptionhandling.exceptions.NotSuperHeroFoundException;
import com.jars.superheroesspringapi.exceptionhandling.exceptions.NotSuperHeroesException;
import com.jars.superheroesspringapi.respository.SuperHeroesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SuperHeroServiceTest {
    @MockBean
    private SuperHeroesRepository superHeroesRepository;

    @Autowired
    private SuperHeroesService superHeroesService;

    @Test
    void getAllSuperHeroes_ExpectNotSuperHeroesException() {
        try{
            //Arrange
            List<SuperHero> superHeroesList = new ArrayList<>();

            //Act
            when(superHeroesRepository.findAll()).thenReturn(superHeroesList);
            List<SuperHero> result = superHeroesService.getAllSuperHeroes();
            fail("Must run the NotSuperHeroesException before this");
        }catch(NotSuperHeroesException e){
            assertEquals("There are not super heroes in the database", e.getMessage());
        }
    }

    @Test
    void getAllSuperHeroes_ExpectSuperHeroesList() {
        //Arrange
        List<SuperHero> superHeroesList = new ArrayList<>();
        superHeroesList.add(new SuperHero(1, "Superman"));
        superHeroesList.add(new SuperHero(2, "Batman"));
        superHeroesList.add(new SuperHero(3, "Spiderman"));
        superHeroesList.add(new SuperHero(4, "Manolito El Fuerte"));
        superHeroesList.add(new SuperHero(5, "El flautista"));
        superHeroesList.add(new SuperHero(6, "Robert Widow"));

        //Act
        when(superHeroesRepository.findAll()).thenReturn(superHeroesList);
        List<SuperHero> result = superHeroesService.getAllSuperHeroes();

        //Assert
        assertEquals(superHeroesList, result);
        assertEquals(superHeroesList.size(), result.size());
    }


    @Test
    void getSuperHeroById_ExpectNotSuperHeroFoundException() {
        try{
            //Arrange
            List<SuperHero> superHeroesList = new ArrayList<>();
            superHeroesList.add(new SuperHero(1, "Superman"));
            superHeroesList.add(new SuperHero(2, "Batman"));
            superHeroesList.add(new SuperHero(3, "Spiderman"));
            superHeroesList.add(new SuperHero(4, "Manolito El Fuerte"));
            superHeroesList.add(new SuperHero(5, "El flautista"));
            superHeroesList.add(new SuperHero(6, "Robert Widow"));

            //Act
            SuperHero superHeroMock = superHeroesList.stream().filter(superHero -> superHero.getId() == 23L).findFirst().orElse(null);
            when(superHeroesRepository.findById(23L)).thenReturn(Optional.ofNullable(superHeroMock));
            SuperHero result = superHeroesService.getSuperHeroByID(23L);
            fail("Must run the NotSuperHeroFoundException before this");
        }catch(NotSuperHeroFoundException e){
            assertEquals("There are not super hero with that ID", e.getMessage());
        }
    }

    @Test
    void getSuperHeroesByContainsName_ExpectNotSuperHeroesException() {
        try {
            //Arrange
            List<SuperHero> superHeroesList = new ArrayList<>();
            superHeroesList.add(new SuperHero(1, "Superman"));
            superHeroesList.add(new SuperHero(2, "Batman"));
            superHeroesList.add(new SuperHero(3, "Spiderman"));
            superHeroesList.add(new SuperHero(4, "Manolito El Fuerte"));
            superHeroesList.add(new SuperHero(5, "El flautista"));
            superHeroesList.add(new SuperHero(6, "Robert Widow"));

            //Act
            String partialName = "conoo";
            List<SuperHero> resultSuperHeroesList = superHeroesList.stream().filter(superHero -> superHero.getName()
                    .toLowerCase().contains(partialName)).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
            when(superHeroesRepository.findByNameContaining(partialName)).thenReturn(resultSuperHeroesList);
            List<SuperHero> result = superHeroesService.getSuperHeroByPartOfName(partialName);
            fail("Must run the NotSuperHeroesException before this");
        }catch(NotSuperHeroesException e){
            //Assert
            assertEquals("There are not super heroes in the database", e.getMessage());
        }
    }

    @Test
    void getSuperHeroesByContainsName_ExpectSuperHeroesList() {
        //Arrange
        List<SuperHero> superHeroesList = new ArrayList<>();
        superHeroesList.add(new SuperHero(1, "Superman"));
        superHeroesList.add(new SuperHero(2, "Batman"));
        superHeroesList.add(new SuperHero(3, "Spiderman"));
        superHeroesList.add(new SuperHero(4, "Manolito El Fuerte"));
        superHeroesList.add(new SuperHero(5, "El flautista"));
        superHeroesList.add(new SuperHero(6, "Robert Widow"));

        //Act
        String partialName = "man";
        List<SuperHero> resultSuperHeroesList = superHeroesList.stream().filter(superHero -> superHero.getName()
                .toLowerCase().contains(partialName)).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        when(superHeroesRepository.findByNameContaining(partialName)).thenReturn(resultSuperHeroesList);
        List<SuperHero> result = superHeroesService.getSuperHeroByPartOfName(partialName);

        //Assert
        assertEquals(resultSuperHeroesList, result);
    }
}
