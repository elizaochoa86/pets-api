package com.javaprojects.petsapi.services;

import com.javaprojects.petsapi.dao.DAO;
import com.javaprojects.petsapi.dto.RaceDTO;
import com.javaprojects.petsapi.dto.SpeciesDTO;
import com.javaprojects.petsapi.entities.Race;
import com.javaprojects.petsapi.entities.Species;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class RaceServiceImpl implements Service<RaceDTO> {

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
        List<RaceDTO> racesDTO = new ArrayList<RaceDTO>();

        for (Race race:
                races) {
            RaceDTO raceDTO = new RaceDTO();

            raceDTO.setId(race.getId());
            raceDTO.setName(race.getName());
            raceDTO.setDescription(race.getDescription());

            SpeciesDTO species = speciesService.convertToSpeciesDTO(race.getSpecies()).orElse(new SpeciesDTO());

            raceDTO.setSpecies(species);

            racesDTO.add(raceDTO);
        }
        return racesDTO;
    }

    private Optional<RaceDTO> convertToRacesDTO(Race race){

        if(race != null){
            RaceDTO raceDTO = new RaceDTO();
            raceDTO.setId(race.getId());
            raceDTO.setName(race.getName());
            raceDTO.setDescription(race.getDescription());
            return Optional.of(raceDTO);
        }

        return Optional.empty();
    }

    private Race convertToRace(RaceDTO raceDTO){

        Race race = new Race();
        race.setId(raceDTO.getId());
        race.setName(raceDTO.getName());
        race.setDescription(raceDTO.getDescription());

        Species species = speciesService.convertToSpecies(raceDTO.getSpecies());

        race.setSpecies(species);

        return race;

    }
}
