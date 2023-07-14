package com.solvd.views.atm;

import static com.solvd.util.AppConfig.ENVIRONMENT;

import com.solvd.views.iviews.atm.IAtmLoginView;

public class AtmLoginView extends AbstractAtmView implements IAtmLoginView {

    @Override
    public String featureTitle() {
        return "Login Menu";
    }

    @Override
    public String getCardNumber() {
        if (ENVIRONMENT.equals("DEV")) {
            display("Admin  Example Card: 1111111111111111");
            display("Client Example Card: 2222222222222213");
        }
        display("Enter your Card Number: ");
        return s.nextLine().trim();
    }

    @Override
    public String getCardPin() {
        if (ENVIRONMENT.equals("DEV")) {
            display("Admin  Example PIN:  8051");
            display("Client Example PIN:  8303");
        }
        display("Enter your Card PIN: ");
        return s.nextLine().trim();
    }

    public String displayUserRequestUnlock() {
        displayBody("Your card is locked.");
        displayBody("Request unlock? ");
        displayBody("1. Yes");
        displayBody("2. No");
        return s.nextLine();
    }

}
