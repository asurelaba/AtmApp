package com.solvd.views.atm;

import com.solvd.interfaces.iviews.atm.IAtmClientView;

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
        display("3. Check Balance");
        display("4. Lock Card");
        display("5. Change PIN");
        display("6. Log Out");
    }

}
