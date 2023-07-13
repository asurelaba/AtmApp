package com.solvd.controllers.icontrollers.atm;

import com.solvd.controllers.icontrollers.IFeatureController;
import com.solvd.db.model.Account;

import java.util.List;

public interface IAdminClientAccountController extends IFeatureController {

    void getAccountId();

    void createAccount();

    void deleteAccount();

    void viewAccounts();

    void validateAccount(int userInput);

    boolean validateUser(int userId);

    void displayAccounts(List<Account> accounts);

}
