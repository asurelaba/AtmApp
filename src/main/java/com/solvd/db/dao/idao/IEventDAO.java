package com.solvd.db.dao.idao;

import com.solvd.db.model.Event;
import java.sql.Timestamp;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/* The operations to get by id, get all from the DB, insert, update,
and delete are covered by the IBaseDao interface*/
public interface IEventDAO extends IBaseDAO<Event> {

    List<Event> getEventsByRangeDate(@Param("from") Timestamp from,
        @Param("to") Timestamp to); // All from BD by Date

    List<Event> getEventsByCardId(int cardId);

    List<Event> getEventsByTypeName(String typeName); // Type: Deposit, Change of pin, etc

    List<Event> getEventsByUserId(int userId); // All events from a single user

    List<Event> getEventsByRangeDateAndUserId(@Param("userId") int userId,
        @Param("from") Timestamp from, @Param("to") Timestamp to); // User's events in a date Range

    List<Event> getEventsByCardNumber(long cardNumber);

    List<Event> getEventsByAccountId(int accountId);

    Event getEventByTransactionId(int transactionId);

}