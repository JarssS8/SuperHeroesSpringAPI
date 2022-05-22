package com.jars.superheroesspringapi.respository;

import com.jars.superheroesspringapi.entity.SuperHero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface SuperHeroesRepository extends JpaRepository<SuperHero, Long> {
}
