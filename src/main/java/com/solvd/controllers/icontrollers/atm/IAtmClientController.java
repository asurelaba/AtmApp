package com.solvd.controllers.icontrollers.atm;

import com.solvd.controllers.icontrollers.IFeatureController;

public interface IAtmClientController extends IFeatureController {

    void handleWithdraw();

    void handleDeposit();

    void handleCheckBalance();

    void handleLockCardRequest();

    void handleChangePin();

    void handleLogout();

}
