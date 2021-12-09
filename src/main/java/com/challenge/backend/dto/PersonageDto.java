package com.challenge.backend.dto;

import com.challenge.backend.model.Personage;

import java.io.Serializable;

public class PersonageDto implements Serializable {

    private String image;
    private String name;

    public PersonageDto(){}

    public PersonageDto(Personage personage){
        this.image = personage.getImage();
        this.name = personage.getName();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
