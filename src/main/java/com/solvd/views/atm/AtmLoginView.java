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
        while (true) {
            try {
                cardNumber = s.nextLong();
                break;
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
        while (true) {
            try {
                pinNumber = s.nextInt();
                if (String.valueOf(pinNumber).length() != 4) {
                    throw new InputMismatchException();
                }
                break;
            } catch (InputMismatchException e) {
                LOG.warn("Invalid input! PIN must be a four-digit number: ");
                s.nextLine();
            }
        }
        return pinNumber;
    }

    public void displayCardLocked() {
        displayBody("Your card is locked.");
        displayBody("Request unlock (yes/no)? ");
    }

    public boolean displayUserRequestUnlock() {
        boolean unlockRequested = false;
        while (true) {
            String input = s.next().toLowerCase();
            if (input.equals("yes")) {
                unlockRequested = true;
                displayBody("Card Unlock Requested.");
                break;
            } else if (input.equals("no")) {
                break;
            } else {
                displayBody("Invalid input! Please enter either 'yes' or 'no': ");
            }
        }
        displayBody("Thank you for using the AtmApp! Good Bye.");
        return unlockRequested;
    }

}