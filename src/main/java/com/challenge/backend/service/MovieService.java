package com.challenge.backend.service;

import com.challenge.backend.dto.MovieDto;
import com.challenge.backend.dto.PersonageDto;
import com.challenge.backend.model.Movie;
import com.challenge.backend.model.Personage;
import com.challenge.backend.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    public void createMovie(Movie movie){
        movieRepository.save(movie);
    }

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
}
