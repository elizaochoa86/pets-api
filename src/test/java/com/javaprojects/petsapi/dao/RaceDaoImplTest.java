package com.javaprojects.petsapi.dao;

import com.javaprojects.petsapi.config.SimpleTestConfig;
import com.javaprojects.petsapi.entities.Race;
import com.javaprojects.petsapi.entities.Species;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration(
        classes = { SimpleTestConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class RaceDaoImplTest {

    @Autowired
    private DAO<Race> dao;

    @Test
    public void getAllTest(){
        List<Race> races = dao.getAll();

        Assert.assertNotNull(races);
        Assert.assertEquals(0, races.get(0).getId());
        Assert.assertEquals("German Shepherd", races.get(0).getName());
        Assert.assertEquals("The German Shepherd is a dog that originated in Germany", races.get(0).getDescription());

        Species species = races.get(0).getSpecies();

        Assert.assertEquals(0, species.getId());
        Assert.assertEquals("Dog", species.getName());
        Assert.assertEquals("Dogs have four legs", species.getDescription());
    }

    @Test
    public void getByIdValidTest(){
        Race race = dao.getById(0);

        Assert.assertEquals(0, race.getId());
        Assert.assertEquals("German Shepherd", race.getName());
        Assert.assertEquals("The German Shepherd is a dog that originated in Germany", race.getDescription());

        Species species = race.getSpecies();

        Assert.assertEquals(0, species.getId());
        Assert.assertEquals("Dog", species.getName());
        Assert.assertEquals("Dogs have four legs", species.getDescription());
    }

    @Test
    public void getByIdInvalidTest(){
        Race race = dao.getById(10);
        Assert.assertNull(race);
    }

    @Test
    public void addTest(){
        Species species = new Species();
        species.setId(0);

        Race race = new Race();
        race.setId(1);
        race.setName("Cocker Spainel");
        race.setDescription("The Cocker Spainel is a dog that originated in Spain");
        race.setSpecies(species);

        dao.add(race);

        Assert.assertEquals(1, dao.getById(1).getId());
        Assert.assertEquals("Cocker Spainel", dao.getById(1).getName());
        Assert.assertEquals("The Cocker Spainel is a dog that originated in Spain", dao.getById(1).getDescription());

        species = dao.getById(1).getSpecies();

        Assert.assertEquals(0, species.getId());
        Assert.assertEquals("Dog", species.getName());
        Assert.assertEquals("Dogs have four legs", species.getDescription());
    }

    @Test
    public void updateTest(){
        Species species = new Species();
        species.setId(0);

        Race race = new Race();
        race.setId(2);
        race.setName("English Cocker Spainel");
        race.setDescription("The English Cocker Spainel is a dog that originated in EUA");
        race.setSpecies(species);

        dao.add(race);

        Assert.assertEquals(2, dao.getById(2).getId());
        Assert.assertEquals("English Cocker Spainel", dao.getById(2).getName());
        Assert.assertEquals("The English Cocker Spainel is a dog that originated in EUA", dao.getById(2).getDescription());

        species = dao.getById(2).getSpecies();

        Assert.assertEquals(0, species.getId());
        Assert.assertEquals("Dog", species.getName());
        Assert.assertEquals("Dogs have four legs", species.getDescription());

        race = new Race();
        race.setId(2);
        race.setName("English Cocker Spainel");
        race.setDescription("The English Cocker Spainel is a dog that originated in England");
        race.setSpecies(species);

        dao.update(race);

        Assert.assertEquals(2, dao.getById(2).getId());
        Assert.assertEquals("English Cocker Spainel", dao.getById(2).getName());
        Assert.assertEquals("The English Cocker Spainel is a dog that originated in England", dao.getById(2).getDescription());
    }

    @Test
    public void deleteTest(){
        Species species = new Species();
        species.setId(0);

        Race race = new Race();
        race.setId(3);
        race.setName("Unknown");
        race.setDescription("Unknown");
        race.setSpecies(species);

        dao.add(race);

        Assert.assertEquals(3, dao.getById(3).getId());

        dao.delete(3);

        Race race1 = dao.getById(3);

        Assert.assertNull(race1);
    }
}
