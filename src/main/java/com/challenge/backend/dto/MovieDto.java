package com.challenge.backend.dto;

import com.challenge.backend.model.Movie;

import java.io.Serializable;
import java.time.LocalDate;

public class MovieDto implements Serializable {

    private String image;
    private String title;
    private LocalDate creationDate;

    public MovieDto(Movie movie) {
        this.image = movie.getImage();
        this.title = movie.getTitle();
        this.creationDate = movie.getCreationDate();
    }

    public MovieDto(){}

    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
