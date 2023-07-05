package com.solvd.db.dao.mybatisDAO;

import com.solvd.db.dao.idao.IEventDAO;
import com.solvd.db.model.Event;
import com.solvd.db.dao.util.MyBatisSqlFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.Date;
import java.util.List;

public class EventDAO implements IEventDAO {

    private final SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();

    @Override
    public void insert(Event event) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IEventDAO ieventDAO = sqlSession.getMapper(IEventDAO.class);
            ieventDAO.insert(event);
            sqlSession.commit();
        }
    }

    @Override
    public void update(Event event) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IEventDAO ieventDAO = sqlSession.getMapper(IEventDAO.class);
            ieventDAO.update(event);
            sqlSession.commit();
        }
    }

    @Override
    public void delete(int eventId) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IEventDAO ieventDAO = sqlSession.getMapper(IEventDAO.class);
            ieventDAO.delete(eventId);
            sqlSession.commit();
        }
    }

    @Override
    public Event getById(int eventId) {
        Event event;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IEventDAO ieventDAO = sqlSession.getMapper(IEventDAO.class);
            event = ieventDAO.getById(eventId);
        }
        return event;
    }

    @Override
    public List<Event> getAll() {
        List<Event> events;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IEventDAO ieventDAO = sqlSession.getMapper(IEventDAO.class);
            events = ieventDAO.getAll();
        }
        return events;
    }

    @Override
    public List<Event> getEventsByRangeDate(Date from, Date to) {
        List<Event> events;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IEventDAO ieventDAO = sqlSession.getMapper(IEventDAO.class);
            events = ieventDAO.getEventsByRangeDate(from, to);
        }
        return events;
    }

    @Override
    public List<Event> getEventsByCardId(int cardId) {
        List<Event> events;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IEventDAO ieventDAO = sqlSession.getMapper(IEventDAO.class);
            events = ieventDAO.getEventsByCardId(cardId);
        }
        return events;
    }

    @Override
    public List<Event> getEventsByType(String typeName) {
        List<Event> events;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IEventDAO ieventDAO = sqlSession.getMapper(IEventDAO.class);
            events = ieventDAO.getEventsByType(typeName);
        }
        return events;
    }

    @Override
    public List<Event> getEventsByUserId(int userId) {
        List<Event> events;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IEventDAO ieventDAO = sqlSession.getMapper(IEventDAO.class);
            events = ieventDAO.getEventsByUserId(userId);
        }
        return events;
    }

    @Override
    public List<Event> getEventsByRangeDateAndUserID(int userId, Date from, Date to) {
        List<Event> events;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IEventDAO ieventDAO = sqlSession.getMapper(IEventDAO.class);
            events = ieventDAO.getEventsByRangeDateAndUserID(userId, from, to);
        }
        return events;
    }

    @Override
    public List<Event> getEventsByCardNumber(long cardNumber) {
        List<Event> events;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IEventDAO ieventDAO = sqlSession.getMapper(IEventDAO.class);
            events = ieventDAO.getEventsByCardNumber(cardNumber);
        }
        return events;
    }

    @Override
    public List<Event> getEventByAccountId(int accountId) {
        List<Event> events;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IEventDAO ieventDAO = sqlSession.getMapper(IEventDAO.class);
            events = ieventDAO.getEventByAccountId(accountId);
        }
        return events;
    }

    @Override
    public List<Event> getEventsByTransactionId(int transactionId) {
        List<Event> events;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IEventDAO ieventDAO = sqlSession.getMapper(IEventDAO.class);
            events = ieventDAO.getEventsByTransactionId(transactionId);
        }
        return events;
    }
}