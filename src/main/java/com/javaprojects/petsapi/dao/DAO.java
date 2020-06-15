package com.javaprojects.petsapi.dao;

import com.javaprojects.petsapi.entities.Pet;

import java.util.List;

public interface DAO<T> {

    List<T> getAll();

    T getById(int id);

    void add(T t);

    void update(T t);

    void delete(T t);
}
