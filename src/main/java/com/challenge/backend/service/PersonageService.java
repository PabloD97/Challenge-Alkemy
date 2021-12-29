
package com.challenge.backend.service;

import com.challenge.backend.dto.PersonageDto;
import com.challenge.backend.model.Personage;
import com.challenge.backend.repository.PersonageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return personageRepository.save(personage);
    }

    public Personage getById(int id) throws Exception{
        if(!personageRepository.existsById(id)){
            throw new Exception("Personage Not Found");
        }
        return personageRepository.findById(id);
    }

    public Personage updatePersonage(int idPersonage, Personage updatedPersonage) throws Exception {
        if(!personageRepository.existsById(idPersonage)){
            throw new Exception("Personage Not Found");
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

    public void deletePersonageById(int idPersonage) throws Exception {
        if(!personageRepository.existsById(idPersonage)){
            throw new Exception("Personage Not Found");
        }
        Personage p = personageRepository.findById(idPersonage);
        p.deleteMovies();
        personageRepository.deleteById(idPersonage);
    }

    public String personageDetails(int idPersonage) throws Exception {
        if(!personageRepository.existsById(idPersonage)){
            throw new Exception("Personage Not Found");
        }
        Personage personage = personageRepository.findById(idPersonage);
        return personage.personageDetails();
    }


    public List<Personage> searchBy(String name, int age, int idMovie ){
        if(!name.equals("_")){
            return personageRepository.findByName(name);
        } else if(age > 0){
            return personageRepository.findByAge(age);
        } else if(idMovie > 0) {
            return personageRepository.findByFilms_Id(idMovie);
        }
        return new ArrayList<>();
    }


    public Personage findById(int idPersonage) throws java.lang.Exception {
        try {
            return personageRepository.findById(idPersonage);
        }catch (java.lang.Exception e){
            throw new Exception("Personage not found");
        }
    }
}

