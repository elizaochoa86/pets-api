package com.javaprojects.petsapi.controllers;

import com.javaprojects.petsapi.dto.RaceDTO;
import com.javaprojects.petsapi.services.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
public class RaceController {

    @Autowired
    private RaceService service;

    @GetMapping("/api/race")
    public List<RaceDTO> getAll(){
        return service.getAll();
    }

    @GetMapping("/api/race/{id}")
    public RaceDTO getById(@PathVariable int id){
        Optional<RaceDTO> raceDTO = service.getById(id);
        if(!raceDTO.isPresent()){
            throw new NotFoundException();
        }
        return raceDTO.get();
    }

    @PostMapping("/api/race")
    public void add(@RequestBody RaceDTO raceDTO){
        service.add(raceDTO);
    }

    @PutMapping("/api/race/{id}")
    public void update(@RequestBody RaceDTO race, @PathVariable int id){
        race.setId(id);
        service.update(race);
    }

    @DeleteMapping("/api/race/{id}")
    public  void delete(@PathVariable int id){
        service.delete(id);
    }

}
