package com.challenge.backend.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private Set<Movie> movies = new HashSet<>();

    public Gender(String name, String image){
        this.name = name;
        this.image = image;
    }

    public Gender() {

    }

    public void addMovie(Movie movie){
        this.movies.add(movie);
    }

    public void deleteMovie(Movie movie){ this.movies.remove(movie);}

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
