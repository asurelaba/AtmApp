package com.solvd.views.atm;

import com.solvd.views.iviews.atm.IAtmLoginView;

public class AtmLoginView extends AbstractAtmView implements IAtmLoginView {

    @Override
    public String featureTitle() {
        return "Login Menu";
    }

    @Override
    public long getCardNumber() {
        display("Enter your Card Number: ");
        return s.nextLong();
    }

    @Override
    public int getCardPin() {
        display("Enter your Card PIN: ");
        return s.nextInt();
    }

}
