package com.challenge.backend.controller;

import com.challenge.backend.model.Personage;
import com.challenge.backend.service.PersonageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class PersonageController {

    @Autowired
    private PersonageService personageService;

    @GetMapping("/characters")
    public ResponseEntity getAll(){
        return new ResponseEntity(personageService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity createPersonage(@RequestBody Personage personage){
        return new ResponseEntity(personageService.createPersonage(personage), HttpStatus.OK);
    }

    @PutMapping("/update/{idPersonage}")
    public ResponseEntity updatePersonage(
            @PathVariable("idPersonage") int idPersonage,
            @RequestBody Personage personage ){
        try {
            return new ResponseEntity<>(personageService.updatePersonage(idPersonage, personage),HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{idPersonage}")
    public ResponseEntity deletePersonage(@PathVariable("idPersonage") int idPersonage){
        try {
            personageService.deletePersonageById(idPersonage);
            return new ResponseEntity("Successfully deleted character", HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    /** 5. Detalle del Personaje **/
    @GetMapping("/personageDetails/{idPersonage}")
    public ResponseEntity personageDetails(@PathVariable("idPersonage") int idPersonage){
        try {
            return new ResponseEntity(personageService.personageDetails(idPersonage), HttpStatus.OK);
        }catch (Exception ex){
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }


    /** 6. Busqueda de Personajes **/
    //TODO: Spring no me permite poner el mismo endpoit a pesar de que sean distintos
    @GetMapping(value = "/personages")
    public ResponseEntity personageSearch(@RequestParam Optional<String> name,
                                          @RequestParam Optional<Integer> age,
                                          @RequestParam Optional<Integer> idMovie
                                          ){
        return new ResponseEntity(personageService.searchBy(
                name.orElse("_"),
                age.orElse(0),
                idMovie.orElse(0)), HttpStatus.OK);
    }
}
