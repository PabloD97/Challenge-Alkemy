package com.challenge.backend.repository;

import com.challenge.backend.model.Personage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonageRepository extends CrudRepository<Personage, Integer> {

    List<Personage> findAll();
}

