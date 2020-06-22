package com.javaprojects.petsapi.services;

import com.javaprojects.petsapi.dao.DAO;
import com.javaprojects.petsapi.dto.SpeciesDTO;
import com.javaprojects.petsapi.entities.Species;
import org.mapstruct.IterableMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SpeciesServiceImpl implements SpeciesService {

    @Autowired
    private DAO<Species> speciesDAO;

    @Override
    @Transactional
    public List<SpeciesDTO> getAll() {
        List<Species> species = speciesDAO.getAll();
        return convertToListSpeciesDTO(species);
    }

    @Override
    @Transactional
    public Optional<SpeciesDTO> getById(int id) {
        Species species = speciesDAO.getById(id);
        return convertToSpeciesDTO(species);
    }

    @Override
    @Transactional
    public void add(SpeciesDTO speciesDTO) {
        speciesDAO.add(convertToSpecies(speciesDTO));
    }

    @Override
    @Transactional
    public void update(SpeciesDTO speciesDTO) {
        speciesDAO.update(convertToSpecies(speciesDTO));
    }

    @Override
    @Transactional
    public void delete(int id) {
        speciesDAO.delete(id);
    }

    private List<SpeciesDTO> convertToListSpeciesDTO(List<Species> species){
        List<SpeciesDTO> speciesDTO = species.stream().map(specie -> {
            return new SpeciesDTO(specie.getId(), specie.getName(), specie.getDescription());
        }).collect(Collectors.toList());

        return speciesDTO;
    }

    @Override
    public Optional<SpeciesDTO> convertToSpeciesDTO(Species species){

        if(species != null){
            SpeciesDTO speciesDTO = new SpeciesDTO(species.getId(), species.getName(), species.getDescription());
            return Optional.of(speciesDTO);
        }

        return Optional.empty();
    }

    @Override
    public Species convertToSpecies(SpeciesDTO speciesDTO){

        Species species = new Species();
        species.setId(speciesDTO.getId());
        species.setName(speciesDTO.getName());
        species.setDescription(speciesDTO.getDescription());
        return species;

    }
}
