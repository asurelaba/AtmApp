package com.solvd.db.dao.mybatisdao;

import com.solvd.db.dao.factory.MyBatisSqlFactory;
import com.solvd.db.dao.idao.IEventTypeDAO;
import com.solvd.db.model.EventType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class EventTypeDAO implements IEventTypeDAO {

    private final SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();

    @Override
    public void insert(EventType eventType) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IEventTypeDAO ieventTypeDAO = sqlSession.getMapper(IEventTypeDAO.class);
            ieventTypeDAO.insert(eventType);
            sqlSession.commit();
        }
    }

    @Override
    public void update(EventType eventType) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IEventTypeDAO ieventTypeDAO = sqlSession.getMapper(IEventTypeDAO.class);
            ieventTypeDAO.update(eventType);
            sqlSession.commit();
        }
    }

    @Override
    public void delete(int eventTypeId) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IEventTypeDAO ieventTypeDAO = sqlSession.getMapper(IEventTypeDAO.class);
            ieventTypeDAO.delete(eventTypeId);
            sqlSession.commit();
        }
    }

    @Override
    public EventType getById(int eventTypeId) {
        EventType eventType;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IEventTypeDAO ieventTypeDAO = sqlSession.getMapper(IEventTypeDAO.class);
            eventType = ieventTypeDAO.getById(eventTypeId);
        }
        return eventType;
    }

    @Override
    public List<EventType> getAll() {
        List<EventType> eventTypes;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IEventTypeDAO ieventTypeDAO = sqlSession.getMapper(IEventTypeDAO.class);
            eventTypes = ieventTypeDAO.getAll();
        }
        return eventTypes;
    }

    @Override
    public EventType getEventTypeByTypeName(String typeName) {
        EventType eventType;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IEventTypeDAO ieventTypeDAO = sqlSession.getMapper(IEventTypeDAO.class);
            eventType = ieventTypeDAO.getEventTypeByTypeName(typeName);
        }
        return eventType;
    }
}
