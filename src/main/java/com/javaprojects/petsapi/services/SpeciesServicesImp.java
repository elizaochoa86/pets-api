package com.javaprojects.petsapi.services;

import com.javaprojects.petsapi.dao.DAO;
import com.javaprojects.petsapi.dto.SpeciesDTO;
import com.javaprojects.petsapi.entities.Race;
import com.javaprojects.petsapi.entities.Species;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SpeciesServicesImp implements Services<SpeciesDTO> {

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
    public void add(Species species) {
        speciesDAO.add(species);

    }

    @Override
    @Transactional
    public void update(Species species) {
        speciesDAO.update(species);
    }

    @Override
    @Transactional
    public void delete(Species species) {

    }

    private List<SpeciesDTO> convertToListSpeciesDTO(List<Species> species){
        List<SpeciesDTO> speciesDTO = new ArrayList<SpeciesDTO>();
        SpeciesDTO speciesDTO1;

        for (Species species1:
             species) {
            speciesDTO1 = new SpeciesDTO();

            speciesDTO1.setId(species1.getId());
            speciesDTO1.setName(species1.getName());
            speciesDTO1.setDescription(species1.getDescription());

            speciesDTO.add(speciesDTO1);
        }
        return speciesDTO;
    }

    private Optional<SpeciesDTO> convertToSpeciesDTO(Species species){

        if(species != null){
            SpeciesDTO speciesDTO = new SpeciesDTO();
            speciesDTO.setId(species.getId());
            speciesDTO.setName(species.getName());
            speciesDTO.setDescription(species.getDescription());
            return Optional.of(speciesDTO);
        }

        return Optional.empty();
    }
}
