package com.solvd.views.atm;

public class AtmView extends AbstractAtmView {

    @Override
    public String featureTitle() {
        return "Welcome to the AtmApp!";
    }

    public void displayWelcome() {
        display(featureTitle());
    }

    public void displayAtmReset() {
        display("ATM Reset In Progress. Please Wait..");
        display(System.lineSeparator());
    }

}
