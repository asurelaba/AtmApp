package com.solvd.views.atm;

import com.solvd.interfaces.iviews.atm.IAtmLoginView;
import java.util.Scanner;

public class AtmLoginView implements IAtmLoginView {

    private static final Scanner s = new Scanner(System.in);

    @Override
    public void display(String message) {
        System.out.println(message);
    }

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

    @Override
    public int getUserSelection() {
        return s.nextInt();
    }

}
