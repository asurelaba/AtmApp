package com.solvd.db.dao.idao;

import com.solvd.db.model.User;

import java.util.List;

/* The operations to get by id, get all from the DB, insert, update,
and delete are covered by the IBaseDao interface*/
public interface IUserDAO extends IBaseDAO<User> {

    List<User> getUsersByStatus(String statusName);

    List<User> getAllUsersByRoleName(String roleName); // RoleName: Client/Admin

    List<User> getUserByName(String firstName, String LastName);

    User getUserByAccountId(int accountId);

    User getUserByCardNumber(long cardNumber);

    User getUserByEventId(int eventId);

    User getUserByTransactionId(int transactionId);
}