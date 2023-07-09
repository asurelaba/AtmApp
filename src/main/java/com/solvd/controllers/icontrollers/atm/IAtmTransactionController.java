package com.solvd.controllers.icontrollers.atm;

import com.solvd.controllers.icontrollers.IFeatureController;

public interface IAtmTransactionController extends IFeatureController {

    void getTransactionAmount();

    double checkBalance();

    int handleInsufficientBalance();

    void updateBalance();

    void recordEvent(String eventType);

    void recordTransaction();

    void printReceiptOrNot();

    void displayReceipt();

    void setEventType();

    String getAdditionalInfo();

    void exit();

    void handleLogout();
}
