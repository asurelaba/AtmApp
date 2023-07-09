package com.solvd.views.iviews.atm;

public interface IAtmTransactionView {

    void displayInsufficientAmountChoices();

    void displayNonexistentAccountChoices();

    void displayPrintReceiptChoices();

    void displayExitChoices();

    void displayLogoutMessage(String userName);

    int getUserChoice();

    double getTransactionAmount();

    int getAccountId();
}
