package com.javaprojects.petsapi.services;

import com.javaprojects.petsapi.dao.DAO;
import com.javaprojects.petsapi.dto.PetDTO;
import com.javaprojects.petsapi.dto.RaceDTO;
import com.javaprojects.petsapi.dto.SpeciesDTO;
import com.javaprojects.petsapi.entities.Pet;
import com.javaprojects.petsapi.entities.Race;
import com.javaprojects.petsapi.entities.Sex;
import com.javaprojects.petsapi.entities.Species;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.*;

public class PetServiceImplTest {
    @Mock
    DAO<Pet> dao;
    PetService service;

    Species specie = new  Species(1, "Dog", "Dog", null);
    Race race = new Race(1, "Cocker Sapniel", "Cocker Sapniel", specie);
    List<Pet> pets = Arrays.asList(
            new Pet(1, "Ramona", 2, Sex.F, race, true, true),
            new Pet(2, "Linda", 5, Sex.F, race, true, true)
    );

    SpeciesDTO speciesDTO = new  SpeciesDTO(1, "Dog", "Dog");
    RaceDTO raceDTO = new RaceDTO(1, "Cocker Sapniel", "Cocker Sapniel", speciesDTO);
    List<PetDTO> petsDTO = Arrays.asList(
            new PetDTO(1, "Ramona", 2, Sex.F, raceDTO, true, true),
            new PetDTO(2, "Linda", 5, Sex.F, raceDTO, true, true)
    );

    @BeforeEach
    void init(){
        initMocks(this);
        service = new PetServiceImpl(dao);
    }

    @Test
    public void getAllTest(){
        when(dao.getAll()).thenReturn(pets);
        assertEquals(petsDTO, service.getAll());
    }

    @Test
    public void getByIdTest(){
        when(dao.getById(0)).thenReturn(pets.get(0));
        assertEquals(Optional.of(petsDTO.get(0)), service.getById(0));
    }

    @Test
    public void addTest(){
        RaceDTO raceDTO = new RaceDTO(3, null, null, null);
        PetDTO petDTO = new PetDTO(3, "Boris", 10, Sex.M, raceDTO, true, true);

        Race race = new Race(3, null, null, null);
        Pet pet = new Pet(3, "Boris", 10, Sex.M, race, true, true);

        service.add(petDTO);
        Mockito.verify(dao, times(1)).add(pet);
    }

    @Test
    public void updateTest(){
        RaceDTO raceDTO = new RaceDTO(1, null, null, null);
        PetDTO petDTO = new PetDTO(1, "Robert", 10, Sex.M, raceDTO, true, true);

        Race race = new Race(1, null, null, null);
        Pet pet = new Pet(1, "Robert", 10, Sex.M, race, true, true);

        service.update(petDTO);
        Mockito.verify(dao, times(1)).update(pet);
    }

    @Test
    public void deleteTest(){
        service.delete(2);
        Mockito.verify(dao, times(1)).delete(2);
    }
}
