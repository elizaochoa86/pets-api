package com.javaprojects.petsapi.dao;

import com.javaprojects.petsapi.entities.Pet;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class PetDAOImpl implements DAO<Pet> {
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Pet> getAll() {
        TypedQuery<Pet> query = entityManager.createQuery("from Pet", Pet.class);
        return query.getResultList();
    }

    @Override
    public Pet getById(int id) {
        return entityManager.find(Pet.class, id);
    }

    @Override
    public void add(Pet pet) {
        Session session = entityManager.unwrap(Session.class);
        session.save(pet);
    }

    @Override
    public void update(Pet pet) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(pet);
    }

    @Override
    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.delete(currentSession.get(Pet.class, id));
    }
}
