package com.solvd.controllers.icontrollers.atm;

import com.solvd.controllers.icontrollers.IFeatureController;

public interface IAtmAdminController extends IFeatureController {

    void handleTransactionQueryMenu();

    void handleCards();

    void handleAddDeleteViewUsers();

    void handleClientAccounts();

    void handleChangePin();

    void handleLogout();

    void handleAppShutdown();

}
