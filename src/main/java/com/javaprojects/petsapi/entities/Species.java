package com.javaprojects.petsapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Species {
    @Id
    private int id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "species")
    @Fetch(FetchMode.SELECT)
    private List<Race> races;
}
