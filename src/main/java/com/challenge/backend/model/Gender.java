package com.challenge.backend.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genders")
public class Gender {

    @Id
    private int id;
    private String name;
    private String image;
    @OneToMany
    private List<Movie> movie;
}
