package com.solvd.db.dao.mybatisdao;

import com.solvd.db.dao.idao.IAccountDAO;
import com.solvd.db.model.Account;
import com.solvd.db.dao.factory.MyBatisSqlFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class AccountDAO implements IAccountDAO {

    private final SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();

    @Override
    public void insert(Account account) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IAccountDAO iaccountDAO = sqlSession.getMapper(IAccountDAO.class);
            iaccountDAO.insert(account);
            sqlSession.commit();
        }
    }

    @Override
    public void update(Account account) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IAccountDAO iaccountDAO = sqlSession.getMapper(IAccountDAO.class);
            iaccountDAO.update(account);
            sqlSession.commit();
        }
    }

    @Override
    public void delete(int accountId) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IAccountDAO iaccountDAO = sqlSession.getMapper(IAccountDAO.class);
            iaccountDAO.delete(accountId);
            sqlSession.commit();
        }
    }

    @Override
    public Account getById(int accountId) {
        Account account;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IAccountDAO iaccountDAO = sqlSession.getMapper(IAccountDAO.class);
            account = iaccountDAO.getById(accountId);
        }
        return account;
    }

    @Override
    public List<Account> getAll() {
        List<Account> accounts;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IAccountDAO iaccountDAO = sqlSession.getMapper(IAccountDAO.class);
            accounts = iaccountDAO.getAll();
        }
        return accounts;
    }

    @Override
    public Account getAccountByUserId(int userId) {
        Account account;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IAccountDAO iaccountDAO = sqlSession.getMapper(IAccountDAO.class);
            account = iaccountDAO.getAccountByUserId(userId);
        }
        return account;
    }
}