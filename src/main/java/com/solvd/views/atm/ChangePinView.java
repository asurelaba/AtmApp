package com.solvd.views.atm;

import java.util.InputMismatchException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ChangePinView extends AbstractAtmView {

    private final Logger LOG = LogManager.getLogger(this.getClass());

    @Override
    public String featureTitle() {
        return "Change Pin";
    }

    public int getNewCardPin() {
        displayBody("Enter your 4-digit new PIN: ");
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
