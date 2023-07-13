package com.solvd.services;

import com.solvd.db.model.Card;
import com.solvd.db.model.Event;
import com.solvd.db.model.EventType;
import com.solvd.db.model.Transaction;
import com.solvd.enums.EnumEventName;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class EventServiceTest {

    EventService eventService;
    Event event;

    @BeforeMethod
    public void setUp() {
        eventService = new EventService();
        event = new Event();
        Card card = new CardService().getCardByCardNumber(2222222222222214L);
        assertEquals(card.getCardId(), 5);
        event.setCard(card);
        event.setEventType(new EventTypeService().getEventTypeByTypeName("Change Pin"));
        event.setDatetime(new Timestamp(System.currentTimeMillis()));
        eventService.insert(event);
    }

    @org.testng.annotations.Test
    public void testGetEventsByRangeDate() {
        try {
            Timestamp from = new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse("2023-06-25").getTime());
            Timestamp toDate = new Timestamp(System.currentTimeMillis() + 1000);
            assertEquals(eventService.getEventsByRangeDate(from, toDate).size(), eventService.getAll().size());
        } catch (ParseException e) {
            assert false;
        }
    }

    @org.testng.annotations.Test
    public void testGetEventsByCardId() {
        List<Event> events = eventService.getEventsByCardId(20);
        assertEquals(events.size(), 2);
    }

    @org.testng.annotations.Test
    public void testGetEventsByTypeName() {
        List<Event> events = eventService.getEventsByTypeName(EnumEventName.BALANCE_INQUIRY.getEventName());
        assertEquals(events.stream().map(Event::getEventType).map(EventType::getEventTypeName).toList().get(0), EnumEventName.BALANCE_INQUIRY.getEventName());
    }

    @org.testng.annotations.Test
    public void testGetEventsByUserId() {
        List<Event> events = eventService.getEventsByUserId(91);
        assertEquals(events.size(), 2);
    }

    @org.testng.annotations.Test
    public void testGetEventsByRangeDateAndUserId() {
        try {
            Timestamp from = new Timestamp(new SimpleDateFormat("yyyy-MM-dd").parse("2023-07-02").getTime());
            Timestamp toDate = new Timestamp(System.currentTimeMillis());
            assertEquals(eventService.getEventsByRangeDateAndUserId(25, from, toDate).size(), 2);
        } catch (ParseException e) {
            assert false;
        }
    }

    @org.testng.annotations.Test
    public void testGetEventsByCardNumber() {
        List<Event> events = eventService.getEventsByCardNumber(2222222222222243L);
        assertEquals(events.size(), 2);
    }

    @org.testng.annotations.Test
    public void testGetEventsByAccountId() {
        List<Event> events = eventService.getEventsByAccountId(12);
        assertEquals(events.size(), 2);
    }

    @org.testng.annotations.Test
    public void testGetEventByTransactionId() {
        Event eventTransaction = new Event();
        Card card = new CardService().getCardByCardNumber(2222222222222214L);
        assertEquals(card.getCardId(), 5);
        eventTransaction.setCard(card);
        eventTransaction.setEventType(new EventTypeService().getEventTypeByTypeName("Change Pin"));
        eventTransaction.setDatetime(new Timestamp(System.currentTimeMillis()));
        eventService.insert(eventTransaction);
        Transaction transaction = new Transaction();
        transaction.setEvent(eventTransaction);
        transaction.setStatus("approved");
        transaction.setAmount(100);
        new TransactionService().insert(transaction);
        assertEquals(eventService.getEventByTransactionId(transaction.getTransactionId()).getEventId(), transaction.getEvent().getEventId());
    }

    @AfterMethod
    public void cleanUp() {
        eventService = new EventService();
        eventService.delete(event.getEventId());
    }
}