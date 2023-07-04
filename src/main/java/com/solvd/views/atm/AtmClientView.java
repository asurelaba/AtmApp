package com.solvd.views.atm;

import com.solvd.interfaces.iviews.atm.IAtmClientView;
import java.util.Scanner;

public class AtmClientView implements IAtmClientView {

    private static final Scanner s = new Scanner(System.in);

    @Override
    public void display(String message) {
        System.out.println(message);
    }

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

    public int getUserSelection() {
        display("Enter selection choice: ");
        return s.nextInt();
    }

}
