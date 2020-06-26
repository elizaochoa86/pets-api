package com.javaprojects.petsapi.services;

import com.javaprojects.petsapi.dao.DAO;
import com.javaprojects.petsapi.dto.SpeciesDTO;
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

public class SpecieServiceImplTest {

    SpecieService service;
    List<Species> species = Arrays.asList(
            new Species(1, "Dog", "Dog", null),
            new Species(2, "Cat", "Cat", null),
            new Species(3, "Bird", "Bird", null)
    );
    List<SpeciesDTO> speciesDTO = Arrays.asList(
            new SpeciesDTO(1, "Dog", "Dog"),
            new SpeciesDTO(2, "Cat", "Cat"),
            new SpeciesDTO(3, "Bird", "Bird")
    );

    @Mock
    DAO<Species> dao;

    @BeforeEach
    public void init(){
        initMocks(this);
        service = new SpecieServiceImpl(dao);
    }

    @Test
    public void getAllTest(){
        when(dao.getAll()).thenReturn(species);
        assertEquals(speciesDTO, service.getAll());
    }

    @Test
    public void getByIdTest(){
        when(dao.getById(0)).thenReturn(species.get(0));
        assertEquals(Optional.of(speciesDTO.get(0)), service.getById(0));
    }

    @Test
    public void addTest(){
        SpeciesDTO speciesDTO = new SpeciesDTO(4, "Cow", "Cow");
        Species specie = new Species(4, "Cow", "Cow", null);
        service.add(speciesDTO);
        Mockito.verify(dao, times(1)).add(specie);
    }

    @Test
    public void updateTest(){
        SpeciesDTO speciesDTO = new SpeciesDTO(1, "Dog", "Dogs");
        Species specie = new Species(1, "Dog", "Dogs", null);
        service.update(speciesDTO);
        Mockito.verify(dao, times(1)).update(specie);
    }

    @Test
    public void deleteTest(){
        service.delete(3);
        Mockito.verify(dao, times(1)).delete(3);
    }
}
