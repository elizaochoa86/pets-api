package com.javaprojects.petsapi.dto;

import com.javaprojects.petsapi.entities.Species;

public class RaceDTO {

    private int id;
    private String name;
    private String description;

    private SpeciesDTO species;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public SpeciesDTO getSpecies() {
        return species;
    }

    public void setSpecies(SpeciesDTO species) {
        this.species = species;
    }
}
