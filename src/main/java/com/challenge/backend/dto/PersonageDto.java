package com.challenge.backend.dto;

import com.challenge.backend.model.Personage;

public class PersonageDto {

    private String image;
    private String name;

    public PersonageDto(){}

    public PersonageDto(Personage personage){
        this.image = personage.getImage();
    }
}
