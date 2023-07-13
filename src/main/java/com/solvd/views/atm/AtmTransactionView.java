package com.solvd.views.atm;

import com.solvd.views.iviews.atm.IAtmTransactionView;
import java.util.InputMismatchException;

public class AtmTransactionView extends AbstractAtmView implements IAtmTransactionView {

    @Override
    public String featureTitle() {
        return "";
    }

    @Override
    public void displayInsufficientAmountChoices() {
        displayBody("Select Your Choice");
        displayBody("1. Go Back To Main Menu");
        displayBody("2. Enter A Smaller Amount");
    }

    @Override
    public void displayNonexistentAccountChoices() {
        displayBody("The recipient's account does not exist.");
        displayBody("Select Your Choice");
        displayBody("1. Go Back To Main Menu");
        displayBody("2. Re-enter The Recipient's Account ID");
    }

    @Override
    public int getUserChoice() {
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
    public double getTransactionAmount() {
        boolean isRunning = false;
        double amount = 0;

        while (!isRunning) {
            try {
                displayBody("Enter transaction amount:");
                amount = s.nextDouble();

                if (amount <= 0) {
                    displayBody("Invalid transaction amount. Amount must be greater than 0.");
                    continue;
                }

                isRunning = true;
            } catch (InputMismatchException e) {
                displayBody("Invalid input. The transaction amount must be a numeric value.");
                s.nextLine();
            }
        }

        return amount;
    }

    @Override
    public int getAccountId() {
        boolean isRunning = false;
        int accountId = 0;

        while (!isRunning) {
            try {
                displayBody("Enter the recipient's account ID:");
                accountId = s.nextInt();

                isRunning = true;
            } catch (InputMismatchException e) {
                displayBody("Invalid input. Account ID must an integer.");
                s.nextLine();
            }
        }

        return accountId;
    }

}
