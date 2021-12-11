package com.challenge.backend.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "genders")
public class Gender {

    @Id
    private int id;
    private String name;
    private String image;
    @ManyToMany
    private Set<Movie> movies;
}
