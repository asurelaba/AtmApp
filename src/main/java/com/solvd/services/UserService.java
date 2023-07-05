package com.solvd.services;

import com.solvd.db.dao.idao.IUserDAO;
import com.solvd.db.model.User;

import java.util.List;

public class UserService extends EntityService<User, IUserDAO> implements IUserDAO {

    @Override
    protected String getTableName() {
        return "users";
    }

    @Override
    public List<User> getUsersByStatus(String statusName) {
        return dao.getUsersByStatus(statusName);
    }

    @Override
    public List<User> getAllUsersByRoleName(String roleName) {
        return dao.getAllUsersByRoleName(roleName);
    }

    @Override
    public List<User> getUsersByName(String firstName, String lastName) {
        return dao.getUsersByName(firstName, lastName);
    }

    @Override
    public User getUserByAccountId(int accountId) {
        return dao.getUserByAccountId(accountId);
    }

    @Override
    public User getUserByCardNumber(long cardNumber) {
        return dao.getUserByCardNumber(cardNumber);
    }

    @Override
    public User getUserByEventId(int eventId) {
        return dao.getUserByEventId(eventId);
    }

    @Override
    public User getUserByTransactionId(int transactionId) {
        return dao.getUserByTransactionId(transactionId);
    }

}
