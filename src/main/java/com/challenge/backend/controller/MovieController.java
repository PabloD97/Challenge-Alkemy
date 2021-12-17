package com.challenge.backend.controller;

import com.challenge.backend.model.Movie;
import com.challenge.backend.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    /** Crud Movie **/

    @PostMapping("/movie/create")
    public ResponseEntity createMovie(@RequestBody Movie movie){
        return new ResponseEntity(movieService.createMovie(movie), HttpStatus.OK);
    }

    @PutMapping("/movie/update/{idMovie}")
    public ResponseEntity updatePersonage(
            @PathVariable("idMovie") int idMovie,
            @RequestBody Movie movie ){
        try {
            return new ResponseEntity<>(movieService.updateMovie(idMovie, movie),HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/movie/delete/{idMovie}")
    public ResponseEntity deletePersonage(@PathVariable("idMovie") int idMovie){
        try {
            movieService.deleteMovieById(idMovie);
            return new ResponseEntity("Successfully deleted movie", HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /** add and delete personage **/

    @PostMapping("/movie/{idMovie}/addPersonage/{idPersonage}")
    public ResponseEntity addPersonage(@PathVariable("idMovie") int idMovie,
                                       @PathVariable("idPersonage") int idPersonage){
        try {
            movieService.addPersonage(idMovie,idPersonage);
            return new ResponseEntity("Character added successfully", HttpStatus.OK);
        }catch (java.lang.Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/movie/{idMovie}/deletePersonage/{idPersonage}")
    public ResponseEntity deletePersonage(@PathVariable("idMovie") int idMovie,
                                       @PathVariable("idPersonage") int idPersonage){
        try {
            movieService.deletePersonage(idMovie,idPersonage);
            return new ResponseEntity("Character successfully eliminated", HttpStatus.OK);
        }catch (java.lang.Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /** 10.Búsqueda de Películas o Series **/
    @GetMapping(value = "/films")
    public ResponseEntity personageSearch(@RequestParam Optional<String> title,
                                          @RequestParam Optional<Integer> genre,
                                          @RequestParam Optional<String> order
    ){
        return new ResponseEntity(movieService.searchBy(
                title.orElse("_"),
                genre.orElse(0),
                order.orElse("_")), HttpStatus.OK);
    }

}
