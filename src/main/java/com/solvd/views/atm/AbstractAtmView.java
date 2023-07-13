package com.solvd.views.atm;

import com.solvd.db.model.User;
import com.solvd.views.iviews.atm.IAtmView;

import java.util.InputMismatchException;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalTime;
import java.util.Scanner;

public abstract class AbstractAtmView implements IAtmView {

    public static final int SCREEN_WIDTH = 100;
    protected static final Scanner s = new Scanner(System.in);
    protected final Logger LOG = LogManager.getLogger(this.getClass());

    @Override
    public int getUserSelection() {
        int userSel;
        do {
            try {
                display("Enter selection choice: ");
                userSel = s.nextInt();
                break;
            } catch (InputMismatchException e) {
                displayBody("Please enter corresponding number for selection");
                clearScanner();
            }
        } while (true);
        return userSel;
    }

    @Override
    public abstract String featureTitle();

    @Override
    public void display(String message) {
        LOG.info(message);
    }

    private void display(String message, String logColor) {
        switch (logColor) {
            case "red" -> LOG.error(message);
            case "yellow" -> LOG.warn(message);
            case "blue" -> LOG.debug(message);
            default -> LOG.info(message);

        }
    }

    public void displayTitle(String message) {
        display(message);
    }

    public void displayBody(String message) {
        display(message);
    }

    /**
     * displays colored log messages
     *
     * @param message  to display
     * @param logColor accepts "red", "yellow", or "blue" (type: String)
     */
    public void displayBody(String message, String logColor) {
        display(message, logColor);
    }

    public void displayExit() {
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

    public String centerAndTrim(String s, int width) {
        return StringUtils.center(s.substring(0, Math.min(s.length(), width)), width);
    }

    public void clearScanner() {
        if (s.hasNextLine()) {
            s.nextLine();
        }
    }

}
