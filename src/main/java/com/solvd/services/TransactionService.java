package com.solvd.services;

import com.solvd.db.dao.idao.ITransactionDAO;
import com.solvd.db.model.Transaction;

import java.sql.Timestamp;
import java.util.List;

public class TransactionService extends EntityService<Transaction, ITransactionDAO> implements
        ITransactionDAO {

    @Override
    protected String getTableName() {
        return "transactions";
    }


    @Override
    public List<Transaction> getTransactionsByStatus(String statusName) {
        return dao.getTransactionsByStatus(statusName);
    }

    @Override
    public Transaction getTransactionByEventId(int eventId) {
        return dao.getTransactionByEventId(eventId);
    }

    @Override
    public List<Transaction> getTransactionsByCardNumber(long cardNumber) {
        return dao.getTransactionsByCardNumber(cardNumber);
    }

    @Override
    public List<Transaction> getTransactionsByRangeDate(Timestamp from, Timestamp to) {
        return dao.getTransactionsByRangeDate(from, to);
    }

    @Override
    public List<Transaction> getTransactionsByUserId(int userId) {
        return dao.getTransactionsByUserId(userId);
    }

    @Override
    public List<Transaction> getTransactionsByRangeDateAndUserId(int userId, Timestamp from, Timestamp to) {
        return dao.getTransactionsByRangeDateAndUserId(userId, from, to);
    }

}
