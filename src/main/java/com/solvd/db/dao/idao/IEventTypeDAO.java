package com.solvd.db.dao.idao;

import com.solvd.db.model.EventType;

/* The operations to get by id, get all from the DB, insert, update,
and delete are covered by the IBaseDao interface*/
public interface IEventTypeDAO extends IBaseDAO<EventType> {

    EventType getEventTypeByTypeName(String typeName);
}