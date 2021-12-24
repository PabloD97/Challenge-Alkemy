package com.challenge.backend.repository;

import com.challenge.backend.model.Gender;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GenderRepository extends CrudRepository<Gender, Integer> {

    Boolean existsByName(String name);
    Gender findById(int idGender);
}

