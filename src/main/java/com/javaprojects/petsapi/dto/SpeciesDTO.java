package com.javaprojects.petsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpeciesDTO {
    private int id;
    private String name;
    private String description;

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof SpeciesDTO
                && this.id == ((SpeciesDTO) obj).id
                && this.name == ((SpeciesDTO) obj).name
                && this.description == ((SpeciesDTO) obj).description);
    }
}
