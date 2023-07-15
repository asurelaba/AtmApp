package com.solvd.services;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import com.solvd.db.model.Transaction;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TransactionServiceTest {

    private Transaction actualTransaction;
    private int userId;
    private Timestamp from;
    private Timestamp to;
    private TransactionService ts;

    @BeforeMethod
    public void setUp() throws ParseException {
        // Get transaction from an event that has a deposit transaction
        ts = new TransactionService();
        actualTransaction = ts.getTransactionByEventId(new EventService()
            .getEventsByTypeName("Withdrawal")
            .get(0)
            .getEventId());
        userId = actualTransaction.getEvent().getCard().getUser().getUserId();
        from = new Timestamp(
            new SimpleDateFormat("yyyy-MM-dd").parse("2023-06-25").getTime());
        to = new Timestamp(System.currentTimeMillis() + 1000);
    }

    /* Match the Name-status of actualTransaction with the Name-status of the first object retrieved
    from the list using the method.*/
    @Test
    public void testGetTransactionsByStatus() {
        String status = actualTransaction.getStatus();
        assertTrue(ts.getTransactionsByStatus(status)
            .stream()
            .anyMatch(transaction -> actualTransaction.equals(transaction)));
    }

    @Test
    public void testGetTransactionByEventId() {
        int eventId = actualTransaction.getEvent().getEventId();
        assertEquals(actualTransaction.getEvent(), ts.getTransactionByEventId(eventId).getEvent());
    }

    @Test
    public void testGetTransactionsByCardNumber() {
        long cardNumber = actualTransaction.getEvent().getCard().getCardNumber();
        assertTrue(ts.getTransactionsByCardNumber(cardNumber)
            .stream()
            .anyMatch(transaction -> actualTransaction.equals(transaction)));
    }

    @Test
    public void testGetTransactionsByDateRange() {
        assertTrue(ts.getTransactionsByDateRange(from, to)
            .stream()
            .anyMatch(transaction -> actualTransaction.equals(transaction)));
    }

    @Test
    public void testGetTransactionsByUserId() {
        assertTrue(ts.getTransactionsByUserId(userId)
            .stream()
            .anyMatch(transaction -> actualTransaction.equals(transaction)));
    }

    @Test
    public void testGetTransactionsByUserIdAndDateRange() {
        assertTrue(ts.getTransactionsByUserIdAndDateRange(userId, from, to)
            .stream()
            .anyMatch(transaction -> actualTransaction.equals(transaction)));
    }

}