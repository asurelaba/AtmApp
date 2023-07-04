package com.solvd.db.dao.interfacesDAO;

import com.solvd.db.model.Account;

/* The operations to get by id, get all from the DB, insert, update,
and delete are covered by the IBaseDao interface*/
public interface IAccountDAO extends IBaseDAO<Account> {

    Account getAccountByUserId(int userId);
}