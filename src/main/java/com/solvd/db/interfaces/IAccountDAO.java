package com.solvd.db.interfaces;

import com.solvd.db.model.Account;

public interface IAccountDAO extends IBaseDAO<Account> {
    /* The operations to get by id, get all from the DB, insert, update,
and delete are covered by the IBaseDao interface*/

    Account getAccountByUserId(int userId);
}