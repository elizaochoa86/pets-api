package com.javaprojects.petsapi.services;

import java.util.List;
import java.util.Optional;

public interface Service<T> {

    List<T> getAll();

    Optional<T> getById(int id);

    void add(T t);

    void update(T t);

    void delete(int id);
}
