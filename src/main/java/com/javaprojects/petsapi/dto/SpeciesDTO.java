package com.javaprojects.petsapi.dto;

import com.javaprojects.petsapi.entities.Race;

import java.util.List;

public class SpeciesDTO {
    private int id;
    private String name;
    private String description;
    private List<Race> races;

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

    public List<Race> getRaces() {
        return races;
    }

    public void setRaces(List<Race> races) {
        this.races = races;
    }
}
