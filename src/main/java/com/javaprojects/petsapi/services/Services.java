package com.javaprojects.petsapi.services;

import com.javaprojects.petsapi.entities.Species;

import java.util.List;
import java.util.Optional;

public interface Services<T> {

    List<T> getAll();

    Optional<T> getById(int id);

    void add(Species species);

    void update(Species species);

    void delete(Species species);
}
