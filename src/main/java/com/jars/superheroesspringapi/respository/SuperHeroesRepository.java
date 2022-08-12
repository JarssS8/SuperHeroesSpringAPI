package com.jars.superheroesspringapi.respository;

import com.jars.superheroesspringapi.entity.SuperHero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SuperHeroesRepository extends JpaRepository<SuperHero, Long> {
    List<SuperHero> findByNameContaining(String partOfName);
}
