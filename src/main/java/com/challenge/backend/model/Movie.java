package com.challenge.backend.model;


import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "films")
public class Movie {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String image;
    private String title;
    private LocalDate creationDate;
    @Column(length = 5)
    private int qualification;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    private Set<Gender> genders;
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private Set<Personage> characters;

    public Movie() {
    }

    public Movie(String image, String title, LocalDate creationDate, int qualification) {
        this.image = image;
        this.title = title;
        this.creationDate = creationDate;
        this.qualification = qualification;
    }

    public void addPersonage(Personage personage) {
        this.characters.add(personage);
        personage.addMovie(this);
    }

    public void deletePersonage(Personage personage) {
        this.characters.remove(personage);
        personage.deleteMovie(this);
    }

    public String movieDetails(){
        return "Movie{" +
                "image:'" + image + '\'' +
                "title:'" + title + '\'' +
                "creation date:'" + creationDate + '\'' +
                "qualification:'" + qualification + '\'' +
                "characters:'" + charactersDetails() + '\'' +
                "genders:'" + gendersDetails() + '\'' +
                "}";
    }

    private List<String> charactersDetails(){
        List<String> result = new ArrayList<>();
        this.characters.stream().forEach((personage) -> {
            result.add(personage.getName());
        });
        return result;
    }

    private List<String> gendersDetails(){
        List<String> result = new ArrayList<>();
        this.genders.stream().forEach((gender) -> {
            result.add(gender.getName());
        });
        return result;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setQualification(int qualification) {
        this.qualification = qualification;
    }

    public int getQualification() {
        return qualification;
    }

    public void addGender(Gender newGender) {
        this.genders.add(newGender);
        newGender.addMovie(this);
    }

    public void deletePersonages(){
        for(Personage character: characters){
            character.deleteMovie(this);
            characters.remove(character);
        }
    }

    public void deleteGender(Gender gender){
        this.genders.remove(gender);
        gender.deleteMovie(this);
    }

    public void deleteGenders(){
        for(Gender gender: genders){
            gender.deleteMovie(this);
            genders.remove(gender);
        }
    }

}
