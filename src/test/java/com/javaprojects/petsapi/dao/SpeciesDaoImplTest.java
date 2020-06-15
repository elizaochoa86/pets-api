package com.javaprojects.petsapi.dao;

import com.javaprojects.petsapi.entities.Species;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@ContextConfiguration(
        classes = { SimpleTestConfig.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class SpeciesDaoImplTest {

    @Autowired
    DAO<Species> dao;

    @Test
    public void getAllTest(){
        List<Species> species = dao.getAll();

        Assert.assertNotNull(species);
        Assert.assertEquals(0, species.get(0).getId());
        Assert.assertEquals("Dog", species.get(0).getName());
        Assert.assertEquals("Dogs have four legs", species.get(0).getDescription());
    }

    @Test
    public void getByIdValidTest(){
        Species species = dao.getById(0);

        Assert.assertEquals(0, species.getId());
        Assert.assertEquals("Dog", species.getName());
        Assert.assertEquals("Dogs have four legs", species.getDescription());
    }

    @Test
    public void getByIdInvalidTest(){
        Species species = dao.getById(10);
        Assert.assertNull(species);
    }

    @Test
    public void add(){
        Species species = new Species();
        species.setId(1);
        species.setName("Bird");
        species.setDescription("Birds has two legs and fly");

        dao.add(species);

        Assert.assertEquals(1, dao.getById(1).getId());
        Assert.assertEquals("Bird", dao.getById(1).getName());
    }

}
