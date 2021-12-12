
package com.challenge.backend.service;

import com.challenge.backend.dto.PersonageDto;
import com.challenge.backend.exception.PersonageException;
import com.challenge.backend.model.Movie;
import com.challenge.backend.model.Personage;
import com.challenge.backend.repository.PersonageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PersonageService {

    @Autowired
    private PersonageRepository personageRepository;


    public List<PersonageDto> getAll(){
        List<Personage> characters = this.personageRepository.findAll();
        List<PersonageDto> charactersDtoList = new ArrayList<>();
        characters.forEach(character -> charactersDtoList.add(new PersonageDto(character)));
        return charactersDtoList;
    }

    public Personage createPersonage(Personage personage){
        //TODO: Borrar esto despues.
        /** Codigo hardcodeado para verificar que funciona el mapeo de las relaciones **/
        LocalDate fecha = LocalDate.now();
        Set<Personage> actores = new HashSet<>();
        personage.setFilms(new HashSet<Movie>());
        actores.add(personage);
        Movie movie1 = new Movie("image","title1", fecha,3, actores);
        Movie movie2 = new Movie("image","title2", fecha,3, actores);
        Movie movie3 = new Movie("image","title3", fecha,3, actores);
        personage.addMovie(movie1);
        personage.addMovie(movie2);
        personage.addMovie(movie3);

        return personageRepository.save(personage);
    }

    public Personage updatePersonage(int idPersonage, Personage updatedPersonage) throws PersonageException {
        if(!personageRepository.existsById(idPersonage)){
            throw new PersonageException("Personage Not Found");
        }
        Personage oldPersonage = personageRepository.findById(idPersonage);
        oldPersonage.setFilms(updatedPersonage.getFilms());
        oldPersonage.setAge(updatedPersonage.getAge());
        oldPersonage.setHistory(updatedPersonage.getHistory());
        oldPersonage.setImage(updatedPersonage.getImage());
        oldPersonage.setName(updatedPersonage.getName());
        oldPersonage.setWeight(updatedPersonage.getWeight());
        return personageRepository.save(oldPersonage);
    }

    public void deletePersonageById(int idPersonage) throws PersonageException {
        if(!personageRepository.existsById(idPersonage)){
            throw new PersonageException("Personage Not Found");
        }
        personageRepository.deleteById(idPersonage);
    }

    public String personageDetails(int idPersonage) throws PersonageException{
        if(!personageRepository.existsById(idPersonage)){
            throw new PersonageException("Personage Not Found");
        }
        Personage personage = personageRepository.findById(idPersonage);
        return personage.personageDetails();
    }
}

