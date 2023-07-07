package com.solvd.services;

import static org.testng.Assert.assertEquals;

import com.solvd.db.model.Person;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PersonServiceTest {

    private static PersonService ps;

    @BeforeMethod
    public void setUp() {
        ps = new PersonService();
    }

    @Test
    public void testGetPersonByName() {
        Person expectedPerson = ps.getById(1);
        assertEquals(ps.getPersonByName("Emma", "Wilson").get(0), expectedPerson);
    }

}