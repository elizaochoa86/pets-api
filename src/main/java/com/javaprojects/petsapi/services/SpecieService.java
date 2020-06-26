package com.javaprojects.petsapi.services;

import com.javaprojects.petsapi.dto.SpeciesDTO;
import com.javaprojects.petsapi.entities.Species;

import java.util.List;
import java.util.Optional;

public interface SpecieService extends Service<SpeciesDTO> {
    static Optional<SpeciesDTO> convertToSpeciesDTO(Species species){

        if(species != null){
            SpeciesDTO speciesDTO = new SpeciesDTO(species.getId(), species.getName(), species.getDescription());
            return Optional.of(speciesDTO);
        }

        return Optional.empty();
    }

    static Species convertToSpecies(SpeciesDTO speciesDTO){
        return new Species(speciesDTO.getId(), speciesDTO.getName(), speciesDTO.getDescription(), null);
    }
}
