package com.challenge.backend.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "characters")
public class Personage {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String image;
    private String name;
    private int age;
    private Double weight;
    private String history;
    //TODO: Esto "funciona"
    @ManyToMany( cascade = {CascadeType.PERSIST})
    private Set<Movie> films;

    public Personage(){}

    public Personage(String image, String name, int age, Double weight, String history) {
        this.image = image;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.history = history;
        this.films = new HashSet<Movie>();
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public Set<Movie> getFilms() {
        return films;
    }

    public void setFilms(Set<Movie> films) {
        this.films = films;
    }

    public void addMovie(Movie movie){
        this.films.add(movie);
        movie.addPersonage(this);
    }

    public void deleteMovie(Movie movie){
        this.films.remove(movie);
        movie.deletePersonage(this);
    }

    public String personageDetails() {
        return "Personage{" +
                "image:'" + image + '\'' +
                ", name:'" + name + '\'' +
                ", age:" + age +
                ", weight:" + weight +
                ", history:'" + history + '\'' +
                ", films:'" + filmsDetails() + '\''+
                '}';
    }

    private List<String> filmsDetails(){
        List<String> result = new ArrayList<>();
        this.films.stream().forEach(movie -> {
            result.add(movie.movieDetails());
        });

        return result;
    }
}
