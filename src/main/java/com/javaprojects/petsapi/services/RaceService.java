package com.javaprojects.petsapi.services;

import com.javaprojects.petsapi.dto.RaceDTO;
import com.javaprojects.petsapi.entities.Race;

import java.util.Optional;

public interface RaceService extends Service<RaceDTO> {
    Optional<RaceDTO> convertToRacesDTO(Race race);
}
