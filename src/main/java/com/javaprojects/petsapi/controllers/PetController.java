package com.javaprojects.petsapi.controllers;

import com.javaprojects.petsapi.dto.PetDTO;
import com.javaprojects.petsapi.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PetController {
    @Autowired
    private PetService service;

    @GetMapping("/api/pet")
    public List<PetDTO> getAll(){
        return service.getAll();
    }

    @GetMapping("/api/pet/{id}")
    public PetDTO getById(@PathVariable int id){
        Optional<PetDTO> petDTO = service.getById(id);
        if(!petDTO.isPresent()){
            throw new NotFoundException();
        }
        return petDTO.get();
    }

    @PostMapping("/api/pet")
    public void add(@RequestBody PetDTO petDTO){
        service.add(petDTO);
    }

    @PutMapping("/api/pet/{id}")
    public void update(@RequestBody PetDTO pet, @PathVariable int id){
        pet.setId(id);
        service.update(pet);
    }

    @DeleteMapping("/api/pet/{id}")
    public  void delete(@PathVariable int id){
        service.delete(id);
    }
}
