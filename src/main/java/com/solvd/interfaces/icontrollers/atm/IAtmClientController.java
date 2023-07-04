package com.solvd.interfaces.icontrollers.atm;

import com.solvd.interfaces.icontrollers.IFeatureController;

public interface IAtmClientController extends IFeatureController {

    void handleWithdraw();

    void handleDeposit();

    void handleCheckBalance();

    void handleLockCardRequest();

    void handleChangePin();

    void handleLogout();

}
