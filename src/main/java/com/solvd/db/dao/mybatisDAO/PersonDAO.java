package com.solvd.db.dao.mybatisDAO;

import com.solvd.db.dao.idao.IPersonDAO;
import com.solvd.db.model.Person;
import com.solvd.util.MyBatisSqlFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class PersonDAO implements IPersonDAO {
    private final SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();

    @Override
    public void insert(Person person) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPersonDAO ipersonDAO = sqlSession.getMapper(IPersonDAO.class);
            ipersonDAO.insert(person);
            sqlSession.commit();
        }
    }

    @Override
    public void update(Person person) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPersonDAO ipersonDAO = sqlSession.getMapper(IPersonDAO.class);
            ipersonDAO.update(person);
            sqlSession.commit();
        }
    }

    @Override
    public void delete(int personId) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPersonDAO ipersonDAO = sqlSession.getMapper(IPersonDAO.class);
            ipersonDAO.delete(personId);
            sqlSession.commit();
        }
    }

    @Override
    public Person getById(int personId) {
        Person person;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPersonDAO ipersonDAO = sqlSession.getMapper(IPersonDAO.class);
            person = ipersonDAO.getById(personId);
        }
        return person;
    }

    @Override
    public List<Person> getAll() {
        List<Person> persons;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPersonDAO ipersonDAO = sqlSession.getMapper(IPersonDAO.class);
            persons = ipersonDAO.getAll();
        }
        return persons;
    }

    @Override
    public Person getPersonByUserId(int userId) {
        Person person;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPersonDAO ipersonDAO = sqlSession.getMapper(IPersonDAO.class);
            person = ipersonDAO.getPersonByUserId(userId);
        }
        return person;
    }

    @Override
    public Person getPersonByName(String personName) {
        Person person;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IPersonDAO ipersonDAO = sqlSession.getMapper(IPersonDAO.class);
            person = ipersonDAO.getPersonByName(personName);
        }
        return person;
    }
}