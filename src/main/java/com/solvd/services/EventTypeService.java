package com.solvd.services;

import com.solvd.db.dao.idao.IEventTypeDAO;
import com.solvd.db.model.EventType;

public class EventTypeService extends EntityService<EventType, IEventTypeDAO> implements
    IEventTypeDAO {

    @Override
    protected String getTableName() {
        return "event_types";
    }

    @Override
    public EventType getEventTypeByTypeName(String typeName) {
        return dao.getEventTypeByTypeName(typeName);
    }

}
