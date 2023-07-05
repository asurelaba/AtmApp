package com.solvd.services;

import com.solvd.db.dao.idao.IAccountDAO;
import com.solvd.db.model.Account;

public class AccountService extends EntityService<Account, IAccountDAO> implements IAccountDAO {

    @Override
    protected String getTableName() {
        return "account";
    }

    @Override
    public Account getAccountByUserId(int userId) {
        return dao.getAccountByUserId(userId);
    }

}
