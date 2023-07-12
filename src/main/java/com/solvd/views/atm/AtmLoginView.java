package com.solvd.views.atm;

import com.solvd.views.iviews.atm.IAtmLoginView;

public class AtmLoginView extends AbstractAtmView implements IAtmLoginView {

    @Override
    public String featureTitle() {
        return "Login Menu";
    }

    @Override
    public String getCardNumber() {
        display("Admin  Example Card: 1111111111111111"); // TODO remove for prod
        display("Client Example Card: 2222222222222213"); // TODO remove for prod
        display("Enter your Card Number: ");
        return s.nextLine().trim();
    }

    @Override
    public String getCardPin() {
        display("Admin  Example PIN:  8051"); // TODO remove for prod
        display("Client Example PIN:  8303"); // TODO remove for prod
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
