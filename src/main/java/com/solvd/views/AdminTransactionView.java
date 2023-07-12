package com.solvd.views;

import com.solvd.views.atm.AbstractAtmView;

import java.sql.Timestamp;
import java.util.InputMismatchException;

public class AdminTransactionView extends AbstractAtmView {

    @Override
    public String featureTitle() {
        return "Transaction Query Menu";
    }

    public void displayAdminTransactionViewMenu() {
        displayTitle(System.lineSeparator());
        displayTitle(featureTitle());
        displayBody("1. Query by Status");
        displayBody("2. Query by Event Id");
        displayBody("3. Query by Card Number");
        displayBody("4. Query by Date");
        displayBody("5. Query by User Id");
        displayBody("6. Query by User Id and Date");
        displayBody("7. return to main menu");
    }

    public int getUserInputInt() {
        while (true) {
            String input = s.next();
            if (input.equalsIgnoreCase("q")) {
                return 1;
            }

            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                displayBody("Invalid input. Please enter a valid integer.");
                displayBody("Press 'q' to quit or enter a valid integer to try again:");
            }
        }
    }

    public long getUserInputLong() {
        while (true) {
            String input = s.next();
            if (input.equalsIgnoreCase("q")) {
                return 1;
            }

            try {
                return Long.parseLong(input);
            } catch (NumberFormatException e) {
                displayBody("Invalid input. Please enter a valid long integer.");
                displayBody("Press 'q' to quit or enter a valid long integer to try again:");
            }
        }
    }

    public String getUserInputString() {
        while (true) {
            String input = s.next();
            if (input.equalsIgnoreCase("q")) {
                return null;
            }

            try {
                return s.next();
            } catch (InputMismatchException e) {
                s.next();
                displayBody("Invalid input. Valid Option: approved");
                displayBody("Press 'q' to quit or enter a valid date to try again:");
            }
        }
    }

    public Timestamp getUserInputDate() {
        while (true) {
            String input = s.next();
            if (input.equalsIgnoreCase("q")) {
                return null;
            }

            try {
                return Timestamp.valueOf(input + " 00:00:00");
            } catch (IllegalArgumentException e) {
                s.nextLine();
                displayBody("Invalid date format. Please enter a valid date in the format 'YYYY-MM-DD'.");
                displayBody("Press 'q' to quit or enter a valid date to try again:");
            }
        }
    }
}
