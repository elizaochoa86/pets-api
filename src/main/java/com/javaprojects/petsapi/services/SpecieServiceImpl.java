package com.javaprojects.petsapi.services;

import com.javaprojects.petsapi.dao.DAO;
import com.javaprojects.petsapi.dto.SpeciesDTO;
import com.javaprojects.petsapi.entities.Species;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SpecieServiceImpl implements SpecieService {

    private DAO<Species> speciesDAO;

    public SpecieServiceImpl(DAO<Species> speciesDAO) {
        this.speciesDAO = speciesDAO;
    }

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
        return SpecieService.convertToSpeciesDTO(species);
    }

    @Override
    @Transactional
    public void add(SpeciesDTO speciesDTO) {
        speciesDAO.add(SpecieService.convertToSpecies(speciesDTO));
    }

    @Override
    @Transactional
    public void update(SpeciesDTO speciesDTO) {
        speciesDAO.update(SpecieService.convertToSpecies(speciesDTO));
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
}
