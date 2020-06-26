package com.javaprojects.petsapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Race {
    @Id
    private int id;
    private String name;
    private String description;

    @ManyToOne
    @JoinColumn(name="species_id", nullable=false)
    private Species species;
}
