package com.javaprojects.petsapi.services;

import com.javaprojects.petsapi.dto.RaceDTO;
import com.javaprojects.petsapi.dto.SpeciesDTO;
import com.javaprojects.petsapi.entities.Race;
import com.javaprojects.petsapi.entities.Species;

import java.util.Optional;

public interface RaceService extends Service<RaceDTO> {
    static Optional<RaceDTO> convertToRacesDTO(Race race){
        if(race != null){
            SpeciesDTO species = SpecieService.convertToSpeciesDTO(race.getSpecies()).orElse(new SpeciesDTO());
            RaceDTO raceDTO = new RaceDTO(race.getId(), race.getName(), race.getDescription(), species);
            return Optional.of(raceDTO);
        }
        return Optional.empty();
    }
}
