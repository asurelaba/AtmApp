package com.solvd.db.dao.idao;

import com.solvd.db.model.Transaction;

import java.sql.Date;
import java.util.List;

/* The operations to get by id, get all from the DB, insert, update,
and delete are covered by the IBaseDao interface*/
public interface ITransactionDAO extends IBaseDAO<Transaction> {

    List<Transaction> getTransactionsByStatus(String statusName); // StatusName: Approved/Denied

    Transaction getTransactionByEventId(int eventId);

    List<Transaction> getTransactionsByCardNumber(long cardNumber);

    List<Transaction> getTransactionsByRangeDate(Date from, Date to); // All data by data range

    List<Transaction> getTransactionsByUserId(int userId); // All user's transaction history

    List<Transaction> getTransactionsByRangeDateAndUserId(int userId, Date from, Date to); // Users data by date
}