package com.solvd.db.interfaces;

import com.solvd.db.model.User;

import java.util.List;

public interface IUserDAO extends IBaseDAO<User> {
            /* The operations to get by id, get all from the DB, insert, update,
    and delete are covered by the IBaseDao interface*/

    List<User> getUsersByStatus(String statusName);

    List<User> getAllUsersByRoleName(String roleName); // RoleName: Client/Admin

    User getUserByName(String userName);

    User getUserByAccountId(int accountId);

    User getUserByCardNumber(long cardNumber);

    User getUserByEventId(int eventId);

    User getUserByTransactionId(int transactionId);
}