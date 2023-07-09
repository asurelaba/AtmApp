package com.solvd.views.atm;

import com.solvd.db.model.Card;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.InputMismatchException;

public class ChangePinView extends AbstractAtmView {

    private final Logger LOG = LogManager.getLogger(this.getClass());

    @Override
    public String featureTitle() {
        return "Change Pin";
    }

    public int getCardPin() {
        displayBody("Enter your new PIN: ");
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
}
