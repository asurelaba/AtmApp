package com.solvd.services;

import com.solvd.db.model.Card;
import com.solvd.db.model.Event;
import org.testng.annotations.BeforeMethod;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.testng.Assert.*;

public class EventServiceTest {

    EventService eventService;

    @BeforeMethod
    public void setUp() {
        eventService = new EventService();
    }

    @org.testng.annotations.Test
    public void testGetEventsByRangeDate() {

    }

    @org.testng.annotations.Test
    public void testGetEventsByCardId() {
        List<Event> events =  eventService.getEventsByCardId(5);
        assertEquals(events.size(),2);
    }

    @org.testng.annotations.Test
    public void testGetEventsByType() {
        List<Event> events =  eventService.getEventsByType("Check Balance");
        assertEquals(events.size(),15);
    }

    @org.testng.annotations.Test
    public void testGetEventsByUserId() {
        List<Event> events =  eventService.getEventsByUserId(91);
        assertEquals(events.size(),2);
    }

    @org.testng.annotations.Test
    public void testGetEventsByRangeDateAndUserId() {

    }

    @org.testng.annotations.Test
    public void testGetEventsByCardNumber() {
    }

    @org.testng.annotations.Test
    public void testGetEventsByAccountId() {
    }

    @org.testng.annotations.Test
    public void testGetEventByTransactionId() {
    }
}