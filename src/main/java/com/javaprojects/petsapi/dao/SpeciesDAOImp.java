package com.javaprojects.petsapi.dao;

import com.javaprojects.petsapi.entities.Species;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class SpeciesDAOImp implements DAO<Species> {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Species> getAll() {
        TypedQuery<Species> query = entityManager.createQuery("from Species", Species.class);
        return query.getResultList();
    }

    @Override
    public Species getById(int id) {
        return entityManager.find(Species.class, id);
    }

    @Override
    public void add(Species species) {
       Session session = entityManager.unwrap(Session.class);
        session.save(species);
    }

    @Override
    public void update(Species species) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(species);
    }

    @Override
    public void delete(Species species) {

    }
}