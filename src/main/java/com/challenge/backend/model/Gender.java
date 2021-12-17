package com.challenge.backend.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "genders")
public class Gender {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String name;
    private String image;
    @OneToMany()
    private List<Movie> movies = new ArrayList<>();

    public Gender(String name, String image){
        this.name = name;
        this.image = image;
    }

    public Gender() {

    }

    public void addMovie(Movie movie){
        this.movies.add(movie);
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
