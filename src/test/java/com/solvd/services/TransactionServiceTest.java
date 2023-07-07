package com.solvd.services;

import com.solvd.db.model.Transaction;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Date;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TransactionServiceTest {

    Transaction actualTransaction;
    private TransactionService ts;
    private String status;
    private int eventId;
    private long cardNumber;
    private Date from;
    private Date to;
    private int userId;

    @BeforeMethod
    public void setUp() {
        // Get transaction from an event that has a deposit transaction
        ts = new TransactionService();
        actualTransaction = new TransactionService()
                .getTransactionByEventId(new EventService()
                        .getEventsByType("Withdrawal")
                        .get(0)
                        .getEventId());
        status = actualTransaction.getStatus();
        eventId = actualTransaction.getEvent().getEventId();
        cardNumber = actualTransaction.getEvent().getCard().getCardNumber();
        from = Date.valueOf("2023-07-04");
        to = Date.valueOf("2023-07-05");
        userId = actualTransaction.getEvent().getCard().getUser().getUserId();
    }

    /* Match the Name-status of actualTransaction with the Name-status of the first object retrieved
    from the list using the method.*/
    @Test
    public void testGetTransactionsByStatus() {
        assertEquals(actualTransaction.getStatus(), ts.getTransactionsByStatus(status)
                .get(0)
                .getStatus());
    }

    @Test
    public void testGetTransactionByEventId() {
        assertEquals(actualTransaction.getEvent(), ts.getTransactionByEventId(eventId).getEvent());
    }

    @Test
    public void testGetTransactionsByCardNumber() {
        assertTrue(new TransactionService().getTransactionsByCardNumber(cardNumber)
                .stream()
                .anyMatch(transaction -> actualTransaction.equals(transaction)));
    }

    @Test
    public void testGetTransactionsByRangeDate() {
        assertTrue(ts.getTransactionsByRangeDate(from, to)
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
    public void testGetTransactionsByRangeDateAndUserId() {
        assertTrue(ts.getTransactionsByRangeDateAndUserId(userId, from, to)
                .stream()
                .anyMatch(transaction -> actualTransaction.equals(transaction)));
    }
}