package com.javaprojects.petsapi.controllers;

import com.javaprojects.petsapi.dto.SpeciesDTO;
import com.javaprojects.petsapi.services.SpeciesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
public class SpeciesController {

    @Autowired
    private SpeciesService service;

    @GetMapping("/api/species")
    public List<SpeciesDTO> getAll(){
        return service.getAll();
    }

    @GetMapping("/api/species/{id}")
    public SpeciesDTO getById(@PathVariable int id){
        Optional<SpeciesDTO> species = service.getById(id);
        if(!species.isPresent()){
            throw new NotFoundException();
        }
        return species.get();
    }

    @PostMapping("/api/species")
    public void add(@RequestBody SpeciesDTO speciesDTO){
        service.add(speciesDTO);
    }

    @PutMapping("/api/species/{id}")
    public void update(@RequestBody SpeciesDTO speciesDTO, @PathVariable int id){
        if(!service.getById(id).isPresent()){
            throw new NotFoundException();
        }
        speciesDTO.setId(id);
        service.update(speciesDTO);
    }

    @DeleteMapping("/api/species/{id}")
    public  void delete(@PathVariable int id){
        if(!service.getById(id).isPresent()){
            throw new NotFoundException();
        }
        service.delete(id);
    }
}
