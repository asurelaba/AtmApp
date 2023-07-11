package com.solvd.views.atm;

import com.solvd.db.model.User;
import com.solvd.views.iviews.atm.IAtmView;

import java.time.LocalTime;
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

    /**
     * displays colored log messages
     * @param message to display
     * @param logColor accepts "red", "yellow", or "blue" (type: String)
     */
    public void display(String message, String logColor) {
        switch (logColor){
            case "red" -> LOG.error(message);
            case "yellow" -> LOG.warn(message);
            case "blue" -> LOG.debug(message);
            default -> LOG.info(message);

        }
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

    public void displayBody(String message, String logType) {
        display(message,logType);
    }

    public void displayExit() {
        display("-".repeat(50));
        display("Enter 0 to Exit");
    }

    public void displayGreeting(User user) {
        int hour = LocalTime.now().getHour();
        String greetType = "";
        if (hour < 12) {
            greetType = "Good Morning, ";
        } else if (hour < 18) {
            greetType = "Good Afternoon, ";
        } else {
            greetType = "Good Evening, ";
        }
        displayBody(greetType + user.getPerson().getFirstName() + ".");

    }

}
