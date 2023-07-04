package com.solvd.db.interfaces;

import com.solvd.db.model.Event;

import java.sql.Date;
import java.util.List;

public interface IEventDAO extends IBaseDAO<Event> {
            /* The operations to get by id, get all from the DB, insert, update,
    and delete are covered by the IBaseDao interface*/

    List<Event> getEventsByRangeDate(Date from, Date to); // All from BD by Date

    List<Event> getEventsByCardId(int cardId);

    List<Event> getEventsByType(); // Type: Deposit, Change of pin, etc

    List<Event> getEventsByUserId(int userId); // All events from a single user

    List<Event> getEventsByRangeDateAndUserID(int userid, Date from, Date to); // User's events in a date Range

    List<Event> getEventsByCardNumber(long cardNumber);

    List<Event> getEventByAccountId(int accountId);

    List<Event> getEventByTransactionId(int transactionId);
}