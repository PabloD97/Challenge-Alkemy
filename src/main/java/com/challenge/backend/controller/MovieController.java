package com.challenge.backend.controller;

import com.challenge.backend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public ResponseEntity getAll(){
        return new ResponseEntity(movieService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/movie/{idMovie}")
    public ResponseEntity getMovieDetails(@PathVariable("idMovie") int idMovie){
        try {
            return new ResponseEntity(movieService.movieDetails(idMovie), HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
