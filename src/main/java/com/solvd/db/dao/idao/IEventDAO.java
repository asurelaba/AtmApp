package com.solvd.db.dao.idao;

import com.solvd.db.model.Event;

import java.sql.Date;
import java.util.List;

/* The operations to get by id, get all from the DB, insert, update,
and delete are covered by the IBaseDao interface*/
public interface IEventDAO extends IBaseDAO<Event> {

    List<Event> getEventsByRangeDate(Date from, Date to); // All from BD by Date

    List<Event> getEventsByCardId(int cardId);

    List<Event> getEventsByType(String typeName); // Type: Deposit, Change of pin, etc

    List<Event> getEventsByUserId(int userId); // All events from a single user

    List<Event> getEventsByRangeDateAndUserId(int userId, Date from, Date to); // User's events in a date Range

    List<Event> getEventsByCardNumber(long cardNumber);

    List<Event> getEventsByAccountId(int accountId);

    Event getEventByTransactionId(int transactionId);
}