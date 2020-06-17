package com.javaprojects.petsapi.dao;

import com.javaprojects.petsapi.entities.Race;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class RaceDAOImpl implements DAO<Race> {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Race> getAll() {
        TypedQuery<Race> query = entityManager.createQuery("from Race", Race.class);
        return query.getResultList();
    }

    @Override
    public Race getById(int id) {
        return entityManager.find(Race.class, id);
    }

    @Override
    public void add(Race race) {
        Session session = entityManager.unwrap(Session.class);
        session.save(race);
    }

    @Override
    public void update(Race race) {
        Session session = entityManager.unwrap(Session.class);
        session.saveOrUpdate(race);
    }

    @Override
    public void delete(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.delete(currentSession.get(Race.class, id));
    }
}
