package com.solvd.views;

import com.solvd.views.atm.AbstractAtmView;

import java.sql.Timestamp;

public class AdminTransactionView extends AbstractAtmView {

    @Override
    public String featureTitle() {
        return "Transaction Query Menu";
    }

    public void displayAdminTransactionViewMenu() {
        display(System.lineSeparator());
        display(featureTitle());
        display("1. Query by Status");
        display("2. Query by Event Id");
        display("3. Query by Card Number");
        display("4. Query by Date");
        display("5. Query by User Id");
        display("6. Query by User Id and Date");
        display("7. return to main menu");
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
                display("Invalid input. Please enter a valid integer.");
                display("Press 'q' to quit or enter a valid integer to try again:");
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
                display("Invalid input. Please enter a valid long integer.");
                display("Press 'q' to quit or enter a valid long integer to try again:");
            }
        }
    }

    public String getUserInputString() {
        return s.next();
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
                display("Invalid date format. Please enter a valid date in the format 'YYYY-MM-DD'.");
                display("Press 'q' to quit or enter a valid date to try again:");
            }
        }
    }
}
