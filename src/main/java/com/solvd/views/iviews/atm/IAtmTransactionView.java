package com.solvd.views.iviews.atm;

public interface IAtmTransactionView {

    void displayInsufficientAmountChoices();

    void displayNonexistentAccountChoices();

    int getUserChoice();

    double getTransactionAmount();

    int getAccountId();

}
