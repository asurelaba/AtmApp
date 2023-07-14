package com.solvd.views.atm.admin;

import com.solvd.views.atm.AbstractAtmView;
import com.solvd.views.iviews.atm.IAdminClientAccountView;
import java.util.InputMismatchException;

public class AdminClientAccountView extends AbstractAtmView implements IAdminClientAccountView {

    @Override
    public String featureTitle() {
        return "Account Menu";
    }

    @Override
    public void displayAccountMenu() {
        displayTitle(featureTitle());
        display("1. Add An Account");
        display("2. Delete An Account");
        display("3. View Accounts");
        display("0. Go Back To Main Menu");
    }

    @Override
    public void displayNonexistentChoices() {
        displayBody("Account does not exist.");
        displayBody("Select Your Choice");
        displayBody("1. Go Back To Account Menu");
        displayBody("2. Re-enter Account ID");
    }

    @Override
    public int getUserSelectionWithTwoChoices() {
        int userSel;

        while (true) {
            try {
                userSel = getUserSelection();
                if (userSel == 1 || userSel == 2) {
                    break;
                }
                displayBody("Invalid Selection");
            } catch (InputMismatchException e) {
                displayBody("Invalid Selection");
                s.nextLine();
            }
        }

        return userSel;
    }

    @Override
    public int getUserSelectionWithFourChoices() {
        int userSel;

        while (true) {
            try {
                userSel = getUserSelection();
                if (userSel == 1 || userSel == 2 || userSel == 3 || userSel == 0) {
                    break;
                }
                displayBody("Invalid Selection");
            } catch (InputMismatchException e) {
                displayBody("Invalid Selection");
                s.nextLine();
            }
        }

        return userSel;
    }

    @Override
    public int getUserInputWithInteger(String prompt) {
        boolean isRunning = false;
        int input = 0;

        while (!isRunning) {
            try {
                displayBody(prompt);
                input = s.nextInt();

                isRunning = true;
            } catch (InputMismatchException e) {
                displayBody("Invalid input. Input must an integer.");
                s.nextLine();
            }
        }

        return input;
    }

    @Override
    public double getBalance() {
        boolean isRunning = false;
        double balance = 0;

        while (!isRunning) {
            try {
                displayBody("Enter balance amount:");
                balance = s.nextDouble();

                if (balance < 0) {
                    displayBody("Invalid balance amount. Amount cannot be negative.");
                    continue;
                }

                if (balance > 9999999999d) {
                    displayBody(
                        "Balance exceeds the maximum allowed. Please enter a smaller amount.");
                    continue;
                }

                isRunning = true;
            } catch (InputMismatchException e) {
                displayBody("Invalid input. The balance amount must be a numeric value.");
                s.nextLine();
            }
        }

        return balance;
    }

}
