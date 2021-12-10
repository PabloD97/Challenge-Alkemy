
package com.challenge.backend.service;

import com.challenge.backend.dto.PersonageDto;
import com.challenge.backend.exception.PersonageException;
import com.challenge.backend.model.Personage;
import com.challenge.backend.repository.PersonageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

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
        personage.setFilms(null);
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
}

