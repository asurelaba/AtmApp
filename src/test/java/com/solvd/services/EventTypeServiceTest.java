package com.solvd.services;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.solvd.db.model.EventType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class EventTypeServiceTest {

    EventTypeService et;

    @BeforeMethod
    public void setUp() {
        et = new EventTypeService();
    }

    @Test
    public void testGetEventTypeByTypeName() {
        EventType withdrawalType = et.getEventTypeByTypeName("Withdrawal");
        assertEquals(withdrawalType.getEventTypeName(), "Withdrawal");
    }

    @Test
    public void testGetAll() {
        List<EventType> allEventTypes = et.getAll();
        Set<EventType> setEventTypes = new HashSet<>(allEventTypes);
        String[] eventTypes = {"Log In", "Log Out", "Lock Card", "Transaction Query",
            "Balance Inquiry", "Print Receipt", "Change Pin", "Unlock Card Request", "Withdrawal",
            "Deposit"
        };
        for (String eventType : eventTypes) {
            assertTrue(setEventTypes.stream()
                .map(EventType::getEventTypeName)
                .collect(Collectors.toSet())
                .contains(eventType));
        }
    }

}