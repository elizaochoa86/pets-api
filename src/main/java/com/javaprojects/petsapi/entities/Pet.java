package com.javaprojects.petsapi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pet {
    @Id
    private int id;
    private String name;
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(length = 1)
    private Sex sex;

    @ManyToOne
    @JoinColumn(name="race", nullable=false)
    private Race race;
    private boolean hasChip;
    private boolean vaccinated;
}
