package com.challenge.backend.repository;

import com.challenge.backend.model.Personage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonageRepository extends CrudRepository<Personage, Integer> {

    List<Personage> findAll();
    Personage findById(int id);

    void deleteById(int id);

    @Query("select p from Personage p where p.name like %?1%")
    List<Personage> findByName(String name);
    List<Personage> findByAge(int age);
    @Query("SELECT p FROM Personage p left join p.films t WHERE t.id = ?1")
    List<Personage> findByFilms_Id(int id);
}

