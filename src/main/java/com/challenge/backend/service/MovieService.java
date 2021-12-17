package com.challenge.backend.service;

import com.challenge.backend.dto.MovieDto;
import com.challenge.backend.model.Gender;
import com.challenge.backend.model.Movie;
import com.challenge.backend.model.Personage;
import com.challenge.backend.repository.GenderRepository;
import com.challenge.backend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenderRepository genderRepository;


    @Autowired
    private PersonageService personageService;

    /** CRUD Movie **/
    public Movie createMovie(Movie movie){
        addGender(movie);
        return movieRepository.save(movie);
    }

    /** metodos para agregar el genero **/
    private Boolean existGender(String name){
        return genderRepository.existsByName(name);
    }

    private void addGender(Movie movie){
        if(!existGender(movie.getGender().getName())){
            Gender gender = new Gender(movie.getGender().getName(), movie.getGender().getImage());
            genderRepository.save(gender);
            gender.addMovie(movie);
            movie.setGender(gender);
        } else {
            Gender genderCurrent = genderRepository.findByName(movie.getGender().getName());
            movie.setGender(genderCurrent);
            genderCurrent.addMovie(movie);
        }
    }

    public Movie updateMovie(int idMovie, Movie updatedMovie) throws Exception {
        if(!movieRepository.existsById(idMovie)){
            throw new Exception("Personage Not Found");
        }
        //TODO: Modificar la forma en la que se actualizan los datos
        Movie oldMovie = movieRepository.findById(idMovie);
        oldMovie.setImage(updatedMovie.getImage());
        oldMovie.setTitle(updatedMovie.getTitle());
        oldMovie.setQualification(updatedMovie.getQualification());
        oldMovie.setCreationDate(updatedMovie.getCreationDate());
        return movieRepository.save(oldMovie);
    }

    public void deleteMovieById(int idMovie) throws Exception {
        if(!movieRepository.existsById(idMovie)){
            throw new Exception("Movie Not Found");
        }
        movieRepository.deleteById(idMovie);
    }

    /****/

    public List<MovieDto> getAll(){
        List<Movie> movies = this.movieRepository.findAll();
        List<MovieDto> moviesDto = new ArrayList<>();
        movies.forEach(movie -> moviesDto.add(new MovieDto(movie)));
        return moviesDto;
    }

    public String movieDetails(int idMovie) throws Exception {
        if(!movieRepository.existsById(idMovie)){
            throw new Exception("Movie Not Found");
        }
        Movie movie = movieRepository.findById(idMovie);
        return movie.movieDetails();
    }

    /** add and delete personage**/
    public void addPersonage(int idMovie ,int idPersonage) throws Exception {
        try {
            Movie movie = movieRepository.findById(idMovie);
            Personage personage = personageService.findById(idPersonage);
            movie.addPersonage(personage);
            movieRepository.save(movie);
        }catch (java.lang.Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePersonage(int idMovie ,int idPersonage) throws Exception{
        try {
            Movie movie = movieRepository.findById(idMovie);
            Personage personage = personageService.findById(idPersonage);
            movie.deletePersonage(personage);
            movieRepository.save(movie);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    /** 10.Búsqueda de Películas o Series **/

    public List<Movie> searchBy(String name, int idGender, String order ){
        //TODO: HACAER REFACTORA ACA
        if(!name.equals("_")){
            return movieRepository.findByTitle(name);
        } else if(idGender > 0){
            return movieRepository.findByGender_Id(idGender);
        } else if(!order.equals("_")) {
            if(order.equals("ASC")){
                return movieRepository.findAllByOrderByCreationDateAsc();
            } else if(order.equals("DESC")){
                return movieRepository.findAllByOrderByCreationDateDesc();
            }
        }
        return new ArrayList<>();
    }


}
