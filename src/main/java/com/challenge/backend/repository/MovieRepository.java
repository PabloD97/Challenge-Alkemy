package com.challenge.backend.repository;

import com.challenge.backend.model.Movie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Integer> {

    List<Movie> findAll();
    Movie findById(int idMovie);

    @Query("select m from Movie m where m.title like %?1%")
    List<Movie> findByTitle(String title);

    List<Movie> findByGender_Id(int id);
    List<Movie> findAllByOrderByCreationDateAsc();
    List<Movie> findAllByOrderByCreationDateDesc();


}
