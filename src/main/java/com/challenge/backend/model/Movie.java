package com.challenge.backend.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "films")
public class Movie {

    @Id
    private int id;
    private String image;
    private String title;
    private LocalDate creationDate;
    @Column(length = 5)
    private int qualification;
    @OneToMany
    private List<Personage> characters;

}
