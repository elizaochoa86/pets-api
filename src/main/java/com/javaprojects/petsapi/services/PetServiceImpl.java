package com.javaprojects.petsapi.services;

import com.javaprojects.petsapi.dao.DAO;
import com.javaprojects.petsapi.dto.PetDTO;
import com.javaprojects.petsapi.dto.RaceDTO;
import com.javaprojects.petsapi.entities.Pet;
import com.javaprojects.petsapi.entities.Race;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements PetService{

    @Autowired
    private DAO<Pet> petDAO;

    @Autowired
    private RaceService raceService;

    @Override
    @Transactional
    public List<PetDTO> getAll() {
        List<Pet> pets = petDAO.getAll();
        return convertToListPetDTO(pets);
    }

    @Override
    @Transactional
    public Optional<PetDTO> getById(int id) {
        return convertToRacesDTO(petDAO.getById(id));
    }

    @Override
    @Transactional
    public void add(PetDTO petDTO) {
        petDAO.add(convertToPet(petDTO));
    }

    @Override
    @Transactional
    public void update(PetDTO petDTO) {
        petDAO.update(convertToPet(petDTO));
    }

    @Override
    @Transactional
    public void delete(int id) {
        petDAO.delete(id);
    }

    private List<PetDTO> convertToListPetDTO(List<Pet> pets){
        List<PetDTO> petsDTO = pets.stream().map(pet -> {
            RaceDTO raceDTO = raceService.convertToRacesDTO(pet.getRace()).orElse(new RaceDTO());
            return new PetDTO(pet.getId(), pet.getName(), pet.getAge(), raceDTO, pet.isHasChip(), pet.isVaccinated());
        }).collect(Collectors.toList());

        return petsDTO;
    }

    private Optional<PetDTO> convertToRacesDTO(Pet pet){

        if(pet != null){
            RaceDTO raceDTO = raceService.convertToRacesDTO(pet.getRace()).orElse(new RaceDTO());
            PetDTO petDTO = new PetDTO(pet.getId(), pet.getName(), pet.getAge(), raceDTO, pet.isHasChip(), pet.isVaccinated());

            return Optional.of(petDTO);
        }

        return Optional.empty();
    }

    private Pet convertToPet(PetDTO petDTO){

        Pet pet = new Pet();
        pet.setId(petDTO.getId());
        pet.setName(petDTO.getName());
        pet.setAge(petDTO.getAge());
        pet.setHasChip(petDTO.isHasChip());
        pet.setVaccinated(petDTO.isVaccinated());

        Race race = new Race();
        race.setId(petDTO.getRace().getId());
        pet.setRace(race);

        return pet;
    }
}
