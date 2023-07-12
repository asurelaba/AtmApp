package com.solvd.views.atm;

import com.solvd.views.iviews.atm.IAtmClientView;

public class AtmClientView extends AbstractAtmView implements IAtmClientView {

    @Override
    public String featureTitle() {
        return "Main Menu";
    }

    @Override
    public void displayClientMenu() {
        display(System.lineSeparator());
        display(featureTitle());
        display("1. Withdraw");
        display("2. Deposit");
        display("3. Transfer");
        display("4. Check Balance");
        display("5. Lock Card");
        display("6. Change PIN");
        display("7. Log Out");
    }

}
