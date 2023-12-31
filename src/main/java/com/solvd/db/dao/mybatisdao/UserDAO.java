package com.solvd.db.dao.mybatisdao;

import com.solvd.db.dao.factory.MyBatisSqlFactory;
import com.solvd.db.dao.idao.IUserDAO;
import com.solvd.db.model.User;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserDAO implements IUserDAO {

    private final SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();

    @Override
    public void insert(User user) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IUserDAO iuserDAO = sqlSession.getMapper(IUserDAO.class);
            iuserDAO.insert(user);
            sqlSession.commit();
        }
    }

    @Override
    public void update(User user) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IUserDAO iuserDAO = sqlSession.getMapper(IUserDAO.class);
            iuserDAO.update(user);
            sqlSession.commit();
        }
    }

    @Override
    public void delete(int userId) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IUserDAO iuserDAO = sqlSession.getMapper(IUserDAO.class);
            iuserDAO.delete(userId);
            sqlSession.commit();
        }
    }

    @Override
    public User getById(int userId) {
        User user;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IUserDAO iuserDAO = sqlSession.getMapper(IUserDAO.class);
            user = iuserDAO.getById(userId);
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> users;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IUserDAO iuserDAO = sqlSession.getMapper(IUserDAO.class);
            users = iuserDAO.getAll();
        }
        return users;
    }

    @Override
    public List<User> getUsersByStatus(String statusName) {
        List<User> users;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IUserDAO iuserDAO = sqlSession.getMapper(IUserDAO.class);
            users = iuserDAO.getUsersByStatus(statusName);
        }
        return users;
    }

    @Override
    public List<User> getAllUsersByRoleName(String roleName) {
        List<User> users;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IUserDAO iuserDAO = sqlSession.getMapper(IUserDAO.class);
            users = iuserDAO.getAllUsersByRoleName(roleName);
        }
        return users;
    }

    @Override
    public List<User> getUsersByName(String firstName, String lastName) {
        List<User> users;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IUserDAO iuserDAO = sqlSession.getMapper(IUserDAO.class);
            users = iuserDAO.getUsersByName(firstName, lastName);
        }
        return users;
    }

    @Override
    public User getUserByAccountId(int accountId) {
        User user;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IUserDAO iuserDAO = sqlSession.getMapper(IUserDAO.class);
            user = iuserDAO.getUserByAccountId(accountId);
        }
        return user;
    }

    @Override
    public User getUserByCardNumber(long cardNumber) {
        User user;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IUserDAO iuserDAO = sqlSession.getMapper(IUserDAO.class);
            user = iuserDAO.getUserByCardNumber(cardNumber);
        }
        return user;
    }

    @Override
    public User getUserByEventId(int eventId) {
        User user;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IUserDAO iuserDAO = sqlSession.getMapper(IUserDAO.class);
            user = iuserDAO.getUserByEventId(eventId);
        }
        return user;
    }

    @Override
    public User getUserByTransactionId(int transactionId) {
        User user;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IUserDAO iuserDAO = sqlSession.getMapper(IUserDAO.class);
            user = iuserDAO.getUserByTransactionId(transactionId);
        }
        return user;
    }

}