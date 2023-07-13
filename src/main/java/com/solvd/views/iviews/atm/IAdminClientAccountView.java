package com.solvd.views.iviews.atm;

public interface IAdminClientAccountView {

    void displayAccountMenu();

    void displayNonexistentChoices();

    int getUserSelectionWithTwoChoices();

    int getUserSelectionWithFourChoices();

    int getUserInputWithInteger(String prompt);

    double getBalance();

}
