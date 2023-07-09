package com.solvd.views.atm;

import com.solvd.views.iviews.atm.IAtmView;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractAtmView implements IAtmView {

    protected final Logger LOG = LogManager.getLogger(this.getClass());

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
        LOG.info(message);
    }

    public void displayTitle(String message) {
        display("\033[H\033[2J");
        display("-".repeat(50));
        display(message);
        display("-".repeat(50));
    }

    public void displayBody(String message) {
        display(message);
    }

    public void displayExit() {
        display("-".repeat(50));
        display("Enter 0 to Exit");
    }
}
