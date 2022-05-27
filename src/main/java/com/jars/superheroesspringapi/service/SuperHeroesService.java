package com.jars.superheroesspringapi.service;

import com.jars.superheroesspringapi.annotations.ExecutionTime;
import com.jars.superheroesspringapi.dto.SuperHeroDto;
import com.jars.superheroesspringapi.entity.SuperHero;
import com.jars.superheroesspringapi.exceptionhandling.exceptions.NotSuperHeroFoundException;
import com.jars.superheroesspringapi.exceptionhandling.exceptions.NotSuperHeroesException;
import com.jars.superheroesspringapi.respository.SuperHeroesRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@Service
public class SuperHeroesService {

    @Autowired
    private SuperHeroesRepository superHeroesRepository;

    @Autowired
    private ModelMapper modelMapper;

    @ExecutionTime
    @Cacheable(value = "all-super-heroes-cache")
    public List<SuperHero> getAllSuperHeroes() {
        List<SuperHero> superHeroesList = superHeroesRepository.findAll();
        if (superHeroesList.isEmpty()) throw new NotSuperHeroesException();
        return superHeroesList;
    }


    @Cacheable(value = "super-hero-cache", key = "'SuperHeroCache'+#id")
    public SuperHero getSuperHeroByID(long id) {
        SuperHero superHero = superHeroesRepository.findById(id).orElse(null);
        if (superHero == null) throw new NotSuperHeroFoundException();
        return superHero;
    }

    @Cacheable(value = "super-hero-cache", key = "'SuperHeroCache'+#partialName")
    public List<SuperHero> getSuperHeroByPartOfName(String partialName) {
        List<SuperHero> superHeroesList = superHeroesRepository.findByNameContaining(partialName);
        if (superHeroesList.isEmpty()) throw new NotSuperHeroesException();
        return superHeroesList;
    }

    public SuperHeroDto updateSuperHero(SuperHero superHeroToModify) {
        SuperHero superHero = superHeroesRepository.findById(superHeroToModify.getId()).orElse(null);
        if (superHero == null) throw new NotSuperHeroFoundException();
        superHero.modifySuperHero(superHeroToModify);
        superHeroesRepository.save(superHero);
        return modelMapper.map(superHero, SuperHeroDto.class);
    }

    public void deleteSuperHero(SuperHero superHero) {
        if (superHeroesRepository.findById(superHero.getId()).orElse(null) == null)
            throw new NotSuperHeroFoundException();
        superHeroesRepository.delete(superHero);
    }


}
