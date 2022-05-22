package com.jars.superheroesspringapi.service;

import com.jars.superheroesspringapi.entity.SuperHero;
import com.jars.superheroesspringapi.exceptionhandling.exceptions.NotSuperHeroFoundException;
import com.jars.superheroesspringapi.exceptionhandling.exceptions.NotSuperHeroesException;
import com.jars.superheroesspringapi.respository.SuperHeroesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperHeroesService {

    @Autowired
    private SuperHeroesRepository superHeroesRepository;
    public List<SuperHero> getAllSuperHeroes() {
        List<SuperHero> superHeroesList = superHeroesRepository.findAll();
        if (superHeroesList.isEmpty()) throw new NotSuperHeroesException();
        return superHeroesList;
    }

    public SuperHero getSuperHeroByID(long id) {
        SuperHero superHero = superHeroesRepository.findById(id).orElse(null);
        if (superHero == null) throw new NotSuperHeroFoundException();
        return superHero;
    }
}
