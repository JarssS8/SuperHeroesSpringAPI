package com.jars.superheroesspringapi.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class SuperHeroDto {
    private long id;
    private String name;
}
