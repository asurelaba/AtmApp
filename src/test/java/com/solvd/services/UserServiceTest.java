package com.solvd.services;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.solvd.db.model.Person;
import com.solvd.db.model.User;
import java.util.List;
import java.util.stream.Collectors;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserServiceTest {

    private UserService us;

    @BeforeMethod
    public void setUp() {
        us = new UserService();
    }

    @Test
    public void testGetUsersByStatus() {
        List<User> adminUsers = us.getUsersByStatus("Active");
        assertTrue(adminUsers.stream()
            .map(User::getPerson)
            .map(Person::getFirstName)
            .collect(Collectors.toSet())
            .contains("Liam"));
    }

    @Test
    public void testGetAllUsersByRoleName() {
        List<User> adminUsers = us.getAllUsersByRoleName("Administrator");
        assertTrue(adminUsers.stream()
            .map(User::getPerson)
            .map(Person::getFirstName)
            .collect(Collectors.toSet())
            .contains("Emma"));
    }

    @Test
    public void testGetUsersByName() {
        List<User> byName = us.getUsersByName("Emily", "Taylor");
        User emilyUser = us.getById(14);
        assertEquals(byName.get(0), emilyUser);
    }

    @Test
    public void testGetUserByAccountId() {
        User user17expected = us.getUserByAccountId(15);
        User user17 = us.getById(17);
        assertEquals(user17expected, user17);
    }

    @Test
    public void testGetUserByCardNumber() {
        User actual = us.getUserByCardNumber(2222222222222220L);
        User user10 = us.getById(10);
        assertEquals(actual, user10);
    }

    @Test
    public void testGetUserByEventId() {
        User eventId15 = us.getUserByEventId(15);
        User userCardId12 = new CardService().getById(12).getUser();
        assertEquals(eventId15, userCardId12);
    }

    @Test
    public void testGetUserByTransactionId() {
        User txnId21 = us.getUserByTransactionId(21);
        User expected = new EventService().getEventByTransactionId(21).getCard().getUser();
        assertEquals(txnId21, expected);
    }

}