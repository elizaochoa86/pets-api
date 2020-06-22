package com.javaprojects.petsapi.services;

import com.javaprojects.petsapi.dto.SpeciesDTO;
import com.javaprojects.petsapi.entities.Species;

import java.util.Optional;

public interface SpeciesService extends Service<SpeciesDTO> {
    Optional<SpeciesDTO> convertToSpeciesDTO(Species species);

    Species convertToSpecies(SpeciesDTO speciesDTO);
}
