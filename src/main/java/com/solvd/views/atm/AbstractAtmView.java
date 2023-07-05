package com.solvd.views.atm;

import com.solvd.interfaces.iviews.atm.IAtmView;
import java.util.Scanner;

public abstract class AbstractAtmView implements IAtmView {

    protected static final Scanner s = new Scanner(System.in);

    @Override
    public int getUserSelection() {
        display("Enter selection choice: ");
        return s.nextInt();
    }

    @Override
    public abstract String featureTitle();

    @Override
    public void display(String message) {
        System.out.println(message);
    }

}
