package com.solvd.db.views;

import com.solvd.db.interfaces.IAtmView;
import java.util.Scanner;

public class AtmView implements IAtmView {

    private static final Scanner s = new Scanner(System.in);


    @Override
    public void display(String message) {
        System.out.println(message);
    }

    @Override
    public String featureTitle() {
        return "Main Menu";
    }

    public long getCardNumber() {
        display("Enter your Card Number: ");
        return s.nextLong();
    }

    public int getCardPin() {
        display("Enter your Card PIN: ");
        return s.nextInt();
    }

    public void displayClientMenu() {
        display(System.lineSeparator());
        display(featureTitle());
        display("1. do example feature1");
        display("2. request card lock");
        display("3. logout");
        display("4. shutdown");
    }

    @Override
    public int getUserSelection() {
        display("Enter selection choice: ");
        return s.nextInt();
    }

    public void displayRequestCardLock() {
        display("Confirm card lock?");
        display("1. yes");
        display("2. no");
    }


}
