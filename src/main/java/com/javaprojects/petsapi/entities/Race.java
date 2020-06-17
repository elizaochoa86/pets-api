package com.javaprojects.petsapi.entities;

import javax.persistence.*;

@Entity
public class Race {
    @Id
    private int id;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name="species_id", nullable=false)
    private Species species;

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

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }
}
