package com.javaprojects.petsapi.dto;

import com.javaprojects.petsapi.entities.Sex;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetDTO {

    private int id;
    private String name;
    private int age;
    private Sex sex;
    private RaceDTO race;
    private boolean hasChip;
    private boolean vaccinated;

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof PetDTO
                && this.id == ((PetDTO) obj).id
                && this.name == ((PetDTO) obj).name
                && this.age == ((PetDTO) obj).age
                && this.sex == ((PetDTO) obj).sex
                && this.race.equals(((PetDTO) obj).race)
                && this.hasChip== ((PetDTO) obj).hasChip
                && this.vaccinated == ((PetDTO) obj).vaccinated);
    }
}
