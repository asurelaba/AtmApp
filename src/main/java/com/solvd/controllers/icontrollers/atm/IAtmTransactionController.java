package com.solvd.controllers.icontrollers.atm;

import com.solvd.enums.EnumEventNames;
import com.solvd.controllers.icontrollers.IFeatureController;

public interface IAtmTransactionController extends IFeatureController {

    void getTransactionAmount();

    double checkBalance();

    int handleInsufficientBalance();

    void updateBalance();

    void recordTransaction();

    EnumEventNames getEventType();

}
