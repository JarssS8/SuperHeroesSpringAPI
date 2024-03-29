package com.jars.superheroesspringapi.entity;

import lombok.*;

import javax.persistence.*;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "superheroes")
public class SuperHero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NonNull
    private String name;

    public void modifySuperHero(SuperHero superHeroToModify) {
        if (!superHeroToModify.getName().isEmpty()){
            this.name = superHeroToModify.getName();
        }
    }

}
