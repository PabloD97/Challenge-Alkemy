package com.challenge.backend.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "characters")
public class Character {

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

}
