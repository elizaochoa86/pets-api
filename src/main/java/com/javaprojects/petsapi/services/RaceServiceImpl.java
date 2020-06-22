package com.javaprojects.petsapi.services;

import com.javaprojects.petsapi.dao.DAO;
import com.javaprojects.petsapi.dto.RaceDTO;
import com.javaprojects.petsapi.dto.SpeciesDTO;
import com.javaprojects.petsapi.entities.Race;
import com.javaprojects.petsapi.entities.Species;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RaceServiceImpl implements RaceService {

    @Autowired
    private DAO<Race> raceDAO;

    @Autowired
    private SpeciesService speciesService;

    @Override
    @Transactional
    public List<RaceDTO> getAll() {

        List<Race> races = raceDAO.getAll();
        return convertToListRaceDTO(races);
    }

    @Override
    @Transactional
    public Optional<RaceDTO> getById(int id) {
        return convertToRacesDTO(raceDAO.getById(id));
    }

    @Override
    @Transactional
    public void add(RaceDTO raceDTO) {
        raceDAO.add(convertToRace(raceDTO));
    }

    @Override
    @Transactional
    public void update(RaceDTO raceDTO) {
        raceDAO.update(convertToRace(raceDTO));
    }

    @Override
    @Transactional
    public void delete(int id) {
        raceDAO.delete(id);
    }

    private List<RaceDTO> convertToListRaceDTO(List<Race> races){
        List<RaceDTO> racesDTO = races.stream().map(race -> {
            SpeciesDTO species = speciesService.convertToSpeciesDTO(race.getSpecies()).orElse(new SpeciesDTO());
            return new RaceDTO(race.getId(), race.getName(), race.getDescription(), species);
        }).collect(Collectors.toList());

        return racesDTO;
    }

    @Override
    public Optional<RaceDTO> convertToRacesDTO(Race race){

        if(race != null){
            SpeciesDTO species = speciesService.convertToSpeciesDTO(race.getSpecies()).orElse(new SpeciesDTO());
            RaceDTO raceDTO = new RaceDTO(race.getId(), race.getName(), race.getDescription(), species);

            return Optional.of(raceDTO);
        }

        return Optional.empty();
    }

    private Race convertToRace(RaceDTO raceDTO){

        Race race = new Race();
        race.setId(raceDTO.getId());
        race.setName(raceDTO.getName());
        race.setDescription(raceDTO.getDescription());

        Species species = new Species();
        species.setId(raceDTO.getSpecies().getId());

        race.setSpecies(species);

        return race;

    }
}
