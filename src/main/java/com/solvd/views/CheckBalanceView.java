package com.solvd.views;

import com.solvd.views.atm.AbstractAtmView;

public class CheckBalanceView extends AbstractAtmView {

    @Override
    public String featureTitle() {
        return "Check Balance";
    }

    public void displayBalance(double clientBalance, String lastFour) {
        displayBody("Current Balance For Card Number Ending In " + lastFour);
        displayBody(String.valueOf(clientBalance));
    }

}