package com.javaprojects.petsapi.services;

import com.javaprojects.petsapi.dao.DAO;
import com.javaprojects.petsapi.dto.RaceDTO;
import com.javaprojects.petsapi.dto.SpeciesDTO;
import com.javaprojects.petsapi.entities.Race;
import com.javaprojects.petsapi.entities.Species;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class RaceServiceImplTest {

    RaceService service;
    Species specie = new Species(1, "Dog", "Dog", null);
    List<Race> races = Arrays.asList(
         new Race(1, "Cocker", "Cocker Spaniel", specie),
         new Race(2, "Pitbull", "Pit Mix", specie)
    );
    SpeciesDTO specieDTO = new SpeciesDTO(1, "Dog", "Dog");
    List<RaceDTO> racesDTO = Arrays.asList(
            new RaceDTO(1, "Cocker", "Cocker Spaniel", specieDTO),
            new RaceDTO(2, "Pitbull", "Pit Mix", specieDTO)
    );

    @Mock
    DAO<Race> dao;

    @BeforeEach
    void init(){
        initMocks(this);
        service = new RaceServiceImpl(dao);
    }

    @Test
    public void getAllTest(){
        when(dao.getAll()).thenReturn(races);
        assertEquals(racesDTO, service.getAll());
    }

    @Test
    public void getByIdTest(){
        when(dao.getById(0)).thenReturn(races.get(0));
        assertEquals(Optional.of(racesDTO.get(0)), service.getById(0));
    }

    @Test
    public void addTest(){
        SpeciesDTO specieDTO = new SpeciesDTO(1, null, null);
        RaceDTO raceDTO = new RaceDTO(3, "Persa", "Persa", specieDTO);

        Species specie = new Species(1, null, null, null);
        Race race = new Race(3, "Persa", "Persa", specie);

        service.add(raceDTO);
        Mockito.verify(dao, times(1)).add(race);
    }

    @Test
    public void updateTest(){
        SpeciesDTO specieDTO = new SpeciesDTO(1, null, null);
        RaceDTO raceDTO = new RaceDTO(1, "Cocker Spaniel", "Cocker Spaniel", specieDTO);

        Species specie = new Species(1, null, null, null);
        Race race = new Race(1, "Cocker Spaniel", "Cocker Spaniel", specie);
        service.update(raceDTO);
        Mockito.verify(dao, times(1)).update(race);
    }

    @Test
    public void deleteTest(){
        service.delete(2);
        Mockito.verify(dao, times(1)).delete(2);
    }
}
