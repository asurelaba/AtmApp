package com.solvd.db.mybatisDAO;

import com.solvd.db.interfaces.IAccountDAO;
import com.solvd.db.model.Account;
import com.solvd.util.MyBatisSqlFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class AccountDAO implements IAccountDAO {
    private final SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();


    @Override
    public void insert(Account account) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            IAccountDAO accountDAO = sqlSession.getMapper(IAccountDAO.class);
            accountDAO.insert(account);
            sqlSession.commit();
        }
    }
    @Override
    public Account getAccountByUserId(int userId) {
        return null;
    }
    @Override
    public void update(Account account) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Account getById(int id) {
        return null;
    }

    @Override
    public List<Account> getAll() {
        return null;
    }
}
