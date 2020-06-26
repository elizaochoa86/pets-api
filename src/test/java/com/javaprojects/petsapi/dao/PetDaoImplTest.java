package com.javaprojects.petsapi.dao;

import com.javaprojects.petsapi.config.SimpleTestConfig;
import com.javaprojects.petsapi.entities.Pet;
import com.javaprojects.petsapi.entities.Race;
import com.javaprojects.petsapi.entities.Sex;
import com.javaprojects.petsapi.entities.Species;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@ContextConfiguration(
        classes = { SimpleTestConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class PetDaoImplTest {
    @Autowired
    private DAO<Pet> dao;

    @Test
    public void getAllTest(){
        List<Pet> pets = dao.getAll();

        assertNotNull(pets);
        assertEquals(0, pets.get(0).getId());
        assertEquals("Cuco", pets.get(0).getName());
        assertEquals(false, pets.get(0).isHasChip());
        assertEquals(false, pets.get(0).isVaccinated());
        assertEquals(2, pets.get(0).getAge());
        assertEquals(Sex.M, pets.get(0).getSex());

        Race race = pets.get(0).getRace();

        assertEquals(0, race.getId());
        assertEquals("German Shepherd", race.getName());
        assertEquals("The German Shepherd is a dog that originated in Germany", race.getDescription());

        Species species = race.getSpecies();

        assertEquals(0, species.getId());
        assertEquals("Dog", species.getName());
        assertEquals("Dogs have four legs", species.getDescription());
    }

    @Test
    public void getByIdValidTest(){
        Pet pet = dao.getById(0);

        assertEquals(0, pet.getId());
        assertEquals("Cuco", pet.getName());
        assertEquals(false, pet.isHasChip());
        assertEquals(false, pet.isVaccinated());
        assertEquals(2, pet.getAge());
        assertEquals(Sex.M, pet.getSex());

        Race race = pet.getRace();

        assertEquals(0, race.getId());
        assertEquals("German Shepherd", race.getName());
        assertEquals("The German Shepherd is a dog that originated in Germany", race.getDescription());

        Species species = race.getSpecies();

        assertEquals(0, species.getId());
        assertEquals("Dog", species.getName());
        assertEquals("Dogs have four legs", species.getDescription());
    }

    @Test
    public void getByIdInvalidTest(){
        Pet pet = dao.getById(10);
        assertNull(pet);
    }

    @Test
    public void addTest(){
        Race race = new Race();
        race.setId(0);
        Species species = new Species();
        species.setId(0);
        race.setSpecies(species);

        Pet pet = new Pet();
        pet.setId(1);
        pet.setName("Pepe");
        pet.setAge(1);
        pet.setSex(Sex.M);
        pet.setHasChip(true);
        pet.setVaccinated(true);
        pet.setRace(race);

        dao.add(pet);

        assertEquals(1, dao.getById(1).getId());
        assertEquals("Pepe", dao.getById(1).getName());
        assertEquals(1, dao.getById(1).getAge());
        assertEquals(Sex.M, dao.getById(1).getSex());
        assertEquals(true, dao.getById(1).isHasChip());
        assertEquals(true, dao.getById(1).isVaccinated());

        race = dao.getById(1).getRace();

        assertEquals(0, race.getId());
        assertEquals("German Shepherd", race.getName());
        assertEquals("The German Shepherd is a dog that originated in Germany", race.getDescription());

        species = race.getSpecies();

        assertEquals(0, species.getId());
        assertEquals("Dog", species.getName());
        assertEquals("Dogs have four legs", species.getDescription());
    }

    @Test
    public void updateTest(){
        Race race = new Race();
        race.setId(0);
        Species species = new Species();
        species.setId(0);
        race.setSpecies(species);

        Pet pet = new Pet();
        pet.setId(2);
        pet.setName("Tobi");
        pet.setAge(10);
        pet.setSex(Sex.M);
        pet.setHasChip(true);
        pet.setVaccinated(true);
        pet.setRace(race);

        dao.add(pet);

        assertEquals(2, dao.getById(2).getId());
        assertEquals("Tobi", dao.getById(2).getName());
        assertEquals(10, dao.getById(2).getAge());
        assertEquals(Sex.M, dao.getById(2).getSex());
        assertEquals(true, dao.getById(2).isHasChip());
        assertEquals(true, dao.getById(2).isVaccinated());

        pet.setId(2);
        pet.setName("Tobi");
        pet.setAge(12);
        pet.setSex(Sex.F);
        pet.setHasChip(true);
        pet.setVaccinated(true);
        pet.setRace(race);

        dao.update(pet);

        assertEquals(2, dao.getById(2).getId());
        assertEquals("Tobi", dao.getById(2).getName());
        assertEquals(12, dao.getById(2).getAge());
        assertEquals(Sex.F, dao.getById(2).getSex());
        assertEquals(true, dao.getById(2).isHasChip());
        assertEquals(true, dao.getById(2).isVaccinated());

    }

    @Test
    public void deleteTest(){
        Race race = new Race();
        race.setId(0);
        Species species = new Species();
        species.setId(0);
        race.setSpecies(species);

        Pet pet = new Pet();
        pet.setId(3);
        pet.setName("Unknown");
        pet.setAge(5);
        pet.setSex(Sex.F);
        pet.setHasChip(true);
        pet.setVaccinated(true);
        pet.setRace(race);

        dao.add(pet);

        Assert.assertEquals(3, dao.getById(3).getId());

        dao.delete(3);

        pet = dao.getById(3);

        Assert.assertNull(pet);
    }
}
