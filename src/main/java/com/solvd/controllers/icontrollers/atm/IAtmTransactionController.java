package com.solvd.controllers.icontrollers.atm;

import com.solvd.controllers.icontrollers.IFeatureController;
import com.solvd.enums.EnumEventName;

public interface IAtmTransactionController extends IFeatureController {

    void getTransactionAmount();

    double checkBalance();

    int handleInsufficientBalance();

    void updateBalance();

    void recordTransaction();

    EnumEventName getEventType();

}
