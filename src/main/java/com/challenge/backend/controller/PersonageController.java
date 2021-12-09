package com.challenge.backend.controller;

import com.challenge.backend.dto.PersonageDto;
import com.challenge.backend.model.Personage;
import com.challenge.backend.service.PersonageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
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
        return new ResponseEntity<>(personageService.updatePersonage(idPersonage, personage),HttpStatus.OK);
    }
}
