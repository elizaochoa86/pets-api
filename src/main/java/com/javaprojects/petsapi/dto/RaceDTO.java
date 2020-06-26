package com.javaprojects.petsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RaceDTO {

    private int id;
    private String name;
    private String description;
    private SpeciesDTO species;

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof RaceDTO
                && this.id == ((RaceDTO) obj).id
                && this.name == ((RaceDTO) obj).name
                && this.description == ((RaceDTO) obj).description
                && this.species.equals(((RaceDTO) obj).species));
    }
}
