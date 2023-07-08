package com.solvd.views.atm;

import com.solvd.views.iviews.atm.IAtmLoginView;
import java.util.InputMismatchException;

public class AtmLoginView extends AbstractAtmView implements IAtmLoginView {

    @Override
    public String featureTitle() {
        return "Login Menu";
    }

    @Override
    public long getCardNumber() {
        display("Admin  Example Card: 1111111111111111"); // TODO remove for prod
        display("Client Example Card: 2222222222222213"); // TODO remove for prod
        display("Enter your Card Number: ");
        long cardNumber = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                cardNumber = s.nextLong();
                validInput = true;
            } catch (InputMismatchException e) {
                LOG.warn("Invalid input! Please enter a valid card number: ");
                s.next(); // Discard the invalid input
            }
        }
        return cardNumber;
    }

    @Override
    public int getCardPin() {
        display("Admin  Example PIN:  8051"); // TODO remove for prod
        display("Client Example PIN:  8303"); // TODO remove for prod
        display("Enter your Card PIN: ");
        int pinNumber = 0;
        boolean validInput = false;
        while (!validInput) {
            try {
                pinNumber = s.nextInt();
                if (String.valueOf(pinNumber).length() != 4) {
                    throw new InputMismatchException();
                }
                validInput = true;
            } catch (InputMismatchException e) {
                LOG.warn("Invalid input! PIN must be a four-digit number: ");
                s.nextLine();
            }
        }
        return pinNumber;
    }

    public boolean displayCardLocked() {
        display("Your card is locked.");
        display("Request unlock (yes/no)? ");

        boolean validInput = false;

        while (!validInput) {
            String input = s.next().toLowerCase();
            if (input.equals("yes")) {
                // Handle "yes" case
                validInput = true;
                return true;
            } else if (input.equals("no")) {
                // Handle "no" case
                validInput = true;
            } else {
                display("Invalid input! Please enter either 'yes' or 'no': ");
            }
        }
        return false;
    }

}
