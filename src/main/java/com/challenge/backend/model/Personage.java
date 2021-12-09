package com.challenge.backend.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "characters")
public class Personage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String image;
    private String name;
    private int age;
    private Double weight;
    private String history;
    @OneToMany
    private List<Movie> films;

    public Personage(){}

    public Personage(String image, String name, int age, Double weight, String history, List<Movie> films) {
        this.image = image;
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.history = history;
        this.films = films;
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

    public List<Movie> getFilms() {
        return films;
    }

    public void setFilms(List<Movie> films) {
        this.films = films;
    }
}
