package com.jars.superheroesspringapi.service;

import com.jars.superheroesspringapi.entity.SuperHero;
import com.jars.superheroesspringapi.respository.SuperHeroesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuperHeroesService {

    @Autowired
    private SuperHeroesRepository superHeroesRepository;
    public List<SuperHero> getAllSuperHeroes() {
        return superHeroesRepository.findAll();
    }
}
