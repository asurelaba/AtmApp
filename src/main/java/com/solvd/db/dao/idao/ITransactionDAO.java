package com.solvd.db.dao.idao;

import com.solvd.db.model.Transaction;
import java.sql.Timestamp;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/* The operations to get by id, get all from the DB, insert, update,
and delete are covered by the IBaseDao interface*/
public interface ITransactionDAO extends IBaseDAO<Transaction> {

    List<Transaction> getTransactionsByStatus(String statusName); // StatusName: Approved/Denied

    Transaction getTransactionByEventId(int eventId);

    List<Transaction> getTransactionsByCardNumber(long cardNumber);

    List<Transaction> getTransactionsByDateRange(@Param("from") Timestamp from,
        @Param("to") Timestamp to); // All data by data range

    List<Transaction> getTransactionsByUserId(int userId); // All user's transaction history

    List<Transaction> getTransactionsByUserIdAndDateRange(@Param("userId") int userId,
        @Param("from") Timestamp from,
        @Param("to") Timestamp to); // Users data by date

}