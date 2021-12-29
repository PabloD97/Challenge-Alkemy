package com.challenge.backend.service;

import com.challenge.backend.dto.MovieDto;
import com.challenge.backend.model.Gender;
import com.challenge.backend.model.Movie;
import com.challenge.backend.model.Personage;
import com.challenge.backend.repository.GenderRepository;
import com.challenge.backend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private GenderRepository genderRepository;


    @Autowired
    private PersonageService personageService;

    /** CRUD Movie **/
    public Movie createMovie(Movie movie) throws Exception{
        if(!validQualification(movie)){
            throw new Exception("Invalid qualification");
        }
        return movieRepository.save(movie);
    }

    public Movie getById(int id) throws Exception{
        if(!movieRepository.existsById(id)){
            throw new Exception("Personage Not Found");
        }
        return movieRepository.findById(id);
    }

    public Boolean validQualification(Movie movie){
        int qualification = movie.getQualification();
        return qualification > 0 && qualification <= 5;
    }

    public Movie updateMovie(int idMovie, Movie updatedMovie) throws Exception {
        if(!movieRepository.existsById(idMovie)){
            throw new Exception("Movie Not Found");
        }
        Movie oldMovie = movieRepository.findById(idMovie);
        oldMovie.setImage(replaceAtribute(updatedMovie.getImage(), oldMovie.getImage()));
        oldMovie.setTitle(replaceAtribute(updatedMovie.getTitle(),oldMovie.getTitle()));
        oldMovie.setQualification(replaceQualification(updatedMovie.getQualification(),oldMovie.getQualification()));
        oldMovie.setCreationDate(replaceDate(updatedMovie.getCreationDate(),oldMovie.getCreationDate()));
        return movieRepository.save(oldMovie);
    }


    private LocalDate replaceDate(LocalDate newDate, LocalDate oldDate){
        if(newDate.equals(oldDate) && newDate != null){
            return oldDate;
        }
        return newDate;
    }

    private Boolean isEqualsQualification(int qualificationUpdate, int qualificationCurrent){

        return (qualificationCurrent == qualificationUpdate) && qualificationUpdate > 0;
    }

    private int replaceQualification(int qualificationUpdate, int qualificationCurrent){
        if(isEqualsQualification(qualificationUpdate,qualificationCurrent)){
            return qualificationUpdate;
        }
        return qualificationCurrent;
    }

    private Boolean isEqualsString(String fieldUpdate, String fieldCurrent){
        return Objects.equals(fieldUpdate, fieldCurrent);
    }

    private String replaceAtribute(String fieldUpdate, String fieldCurrent){
        if(isEqualsString(fieldUpdate, fieldCurrent)){
            return fieldUpdate;
        }else {
            return fieldCurrent;
        }

    }

    public void deleteMovieById(int idMovie) throws Exception {
        if(!movieRepository.existsById(idMovie)){
            throw new Exception("Movie Not Found");
        }
        Movie movie = movieRepository.findById(idMovie);
        movie.deletePersonages();
        movie.deleteGenders();
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
        }catch (Exception e) {
            throw new Exception("Movie or personage not found");
        }
    }

    public void deletePersonage(int idMovie ,int idPersonage) throws Exception{
        try {
            Movie movie = movieRepository.findById(idMovie);
            Personage personage = personageService.findById(idPersonage);
            movie.deletePersonage(personage);
            movieRepository.save(movie);
        }catch (Exception e) {
            throw new Exception("Movie or personage not found");
        }
    }

    /** 10.Búsqueda de Películas o Series **/

    public List<Movie> searchBy(String name, int idGender, String order ){
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

    /** Metodos para agregar y borrar generos de la Movie**/
    public void addGender(int idMovie, int idGender) throws Exception{
        try {
            Movie movie = movieRepository.findById(idMovie);
            Gender gender = genderRepository.findById(idGender);

            movie.addGender(gender);
            movieRepository.save(movie);
        } catch (Exception e){
            throw new Exception("Movie or gender not found");
        }
    }

    public void deleteGender(int idMovie, int idGender) throws Exception{
        try {
            Movie movie = movieRepository.findById(idMovie);
            Gender gender = genderRepository.findById(idGender);

            movie.deleteGender(gender);
            movieRepository.save(movie);
        } catch (Exception e){
            throw new Exception("Movie or gender not found");
        }
    }
}
