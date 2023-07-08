package com.solvd.db.dao.mybatisdao;

import com.solvd.db.dao.factory.MyBatisSqlFactory;
import com.solvd.db.dao.idao.ITransactionDAO;
import com.solvd.db.model.Transaction;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.Timestamp;
import java.util.List;

public class TransactionDAO implements ITransactionDAO {

    private final SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();

    @Override
    public void insert(Transaction transaction) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ITransactionDAO itransactionDAO = sqlSession.getMapper(ITransactionDAO.class);
            itransactionDAO.insert(transaction);
            sqlSession.commit();
        }
    }

    @Override
    public void update(Transaction transaction) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ITransactionDAO itransactionDAO = sqlSession.getMapper(ITransactionDAO.class);
            itransactionDAO.update(transaction);
            sqlSession.commit();
        }
    }

    @Override
    public void delete(int transactionId) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ITransactionDAO itransactionDAO = sqlSession.getMapper(ITransactionDAO.class);
            itransactionDAO.delete(transactionId);
            sqlSession.commit();
        }
    }

    @Override
    public Transaction getById(int transactionId) {
        Transaction transaction;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ITransactionDAO itransactionDAO = sqlSession.getMapper(ITransactionDAO.class);
            transaction = itransactionDAO.getById(transactionId);
        }
        return transaction;
    }

    @Override
    public List<Transaction> getAll() {
        List<Transaction> transactions;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ITransactionDAO itransactionDAO = sqlSession.getMapper(ITransactionDAO.class);
            transactions = itransactionDAO.getAll();
        }
        return transactions;
    }

    @Override
    public List<Transaction> getTransactionsByStatus(String statusName) {
        List<Transaction> transactions;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ITransactionDAO itransactionDAO = sqlSession.getMapper(ITransactionDAO.class);
            transactions = itransactionDAO.getTransactionsByStatus(statusName);
        }
        return transactions;
    }

    @Override
    public Transaction getTransactionByEventId(int eventId) {
        Transaction transaction;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ITransactionDAO itransactionDAO = sqlSession.getMapper(ITransactionDAO.class);
            transaction = itransactionDAO.getTransactionByEventId(eventId);
        }
        return transaction;
    }

    @Override
    public List<Transaction> getTransactionsByCardNumber(long cardNumber) {
        List<Transaction> transactions;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ITransactionDAO itransactionDAO = sqlSession.getMapper(ITransactionDAO.class);
            transactions = itransactionDAO.getTransactionsByCardNumber(cardNumber);
        }
        return transactions;
    }

    @Override
    public List<Transaction> getTransactionsByRangeDate(Timestamp from, Timestamp to) {
        List<Transaction> transactions;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ITransactionDAO itransactionDAO = sqlSession.getMapper(ITransactionDAO.class);
            transactions = itransactionDAO.getTransactionsByRangeDate(from, to);
        }
        return transactions;
    }

    @Override
    public List<Transaction> getTransactionsByUserId(int userId) {
        List<Transaction> transactions;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ITransactionDAO itransactionDAO = sqlSession.getMapper(ITransactionDAO.class);
            transactions = itransactionDAO.getTransactionsByUserId(userId);
        }
        return transactions;
    }

    @Override
    public List<Transaction> getTransactionsByRangeDateAndUserId(int userId, Timestamp from, Timestamp to) {
        List<Transaction> transactions;
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            ITransactionDAO itransactionDAO = sqlSession.getMapper(ITransactionDAO.class);
            transactions = itransactionDAO.getTransactionsByRangeDateAndUserId(userId, from, to);
        }
        return transactions;
    }
}
