package com.solvd.services;

import com.solvd.db.dao.idao.IEventDAO;
import com.solvd.db.model.Event;
import java.sql.Timestamp;
import java.util.List;

public class EventService extends EntityService<Event, IEventDAO> implements IEventDAO {

    @Override
    protected String getTableName() {
        return "events";
    }

    @Override
    public List<Event> getEventsByRangeDate(Timestamp from, Timestamp to) {
        return dao.getEventsByRangeDate(from, to);
    }

    @Override
    public List<Event> getEventsByCardId(int cardId) {
        return dao.getEventsByCardId(cardId);
    }

    @Override
    public List<Event> getEventsByTypeName(String typeName) {
        return dao.getEventsByTypeName(typeName);
    }

    @Override
    public List<Event> getEventsByUserId(int userId) {
        return dao.getEventsByUserId(userId);
    }

    @Override
    public List<Event> getEventsByRangeDateAndUserId(int userId, Timestamp from, Timestamp to) {
        return dao.getEventsByRangeDateAndUserId(userId, from, to);
    }

    @Override
    public List<Event> getEventsByCardNumber(long cardNumber) {
        return dao.getEventsByCardNumber(cardNumber);
    }

    @Override
    public List<Event> getEventsByAccountId(int accountId) {
        return dao.getEventsByAccountId(accountId);
    }

    @Override
    public Event getEventByTransactionId(int transactionId) {
        return dao.getEventByTransactionId(transactionId);
    }

}
