package com.javaprojects.petsapi.controllers;

import com.javaprojects.petsapi.dto.SpeciesDTO;
import com.javaprojects.petsapi.entities.Species;
import com.javaprojects.petsapi.services.Services;
import com.sun.tools.javac.comp.Infer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SpeciesControllers {

    @Autowired
    private Services<SpeciesDTO> services;

    @GetMapping("/api/species")
    public List<SpeciesDTO> getAll(){
        return services.getAll();
    }

    @GetMapping("/api/species/{id}")
    public SpeciesDTO getById(@PathVariable int id){
        Optional<SpeciesDTO> species = services.getById(id);
        if(!species.isPresent()){
            throw new NotFoundException();
        }
        return species.get();
    }

    @PostMapping("/api/species")
    public void add(@RequestBody Species species){
        services.add(species);
    }
}
