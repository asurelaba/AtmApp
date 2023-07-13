package com.solvd.controllers.atm;

import com.solvd.enums.EnumEventNames;
import com.solvd.controllers.icontrollers.atm.IAtmLoginController;
import com.solvd.db.model.Card;
import com.solvd.services.CardService;
import com.solvd.views.atm.AtmLoginView;

public class AtmLoginController implements IAtmLoginController {

    protected Card atmCard;

    private final int MAX_PIN_ATTEMPTS = 3;
    private final AtmLoginView view = new AtmLoginView();

    @Override
    public void run() {
        boolean loggedIn = false;
        int pinAttempts = 0;

        outerLoop:
        while (!loggedIn) {
            handleCardNumberInput();
            if (atmCard == null) {
                view.displayBody("Invalid Card Number.", "yellow");
                continue; // asks for cardNumber again;
            }

            if (isCardLock()) {
                view.displayBody("Card is locked. Enter correct pin to request unlock", "yellow");
                if (handlePinNumberInput()) {
                    handleClientCardLock();
                } else {
                    view.displayBody("Incorrect pin. Goodbye.", "red");
                }
                continue;
            }

            while (!handlePinNumberInput()) {
                pinAttempts++;
                handlePinAttempts(pinAttempts);
                if (pinAttempts == MAX_PIN_ATTEMPTS) {
                    continue outerLoop;
                }
            }

            loggedIn = true;
            logEvent(atmCard, EnumEventNames.LOG_IN);
        }
    }

    private void handlePinAttempts(int pinAttempts) {
        view.display("Invalid Card PIN.");
        if (pinAttempts == MAX_PIN_ATTEMPTS - 2) {
            view.displayBody("2 more attempts remaining.", "yellow");
        } else if (pinAttempts == MAX_PIN_ATTEMPTS - 1) {
            view.displayBody("1 more attempt remaining.", "yellow");
        } else if (pinAttempts == MAX_PIN_ATTEMPTS) {
            atmCard.setStatus("locked");
            new CardService().update(atmCard);
            view.displayBody("Too many login attempts. Locking card.. Goodbye.", "red");
            setAtmCard(null);
        }
    }

    @Override
    public void handleCardNumberInput() {
        long userInputCardNum = validateCardNumber();
        CardService cs = new CardService();
        Card atmCard = cs.getCardByCardNumber(userInputCardNum);
        setAtmCard(atmCard);
    }

    private long validateCardNumber() {
        String cardNumber;
        while (true) {
            try {
                cardNumber = view.getCardNumber();
                if (cardNumber.isEmpty()) {
                    throw new InputMismatchException(
                        "Card number cannot be empty");
                }
                if (cardNumber.length() != 16 || !Pattern.matches("\\d+", cardNumber)) {
                    throw new InputMismatchException("Card number must be comprised of 16 digits");
                }
                break;
            } catch (InputMismatchException e) {
                view.displayBody(e.getMessage(), "yellow");
            }
        }
        return Long.parseLong(cardNumber);
    }

    public boolean handlePinNumberInput() {
        int cardPin = validatePinNumber();
        return cardPin == atmCard.getPin();
    }

    private int validatePinNumber() {
        String cardPin;
        while (true) {
            try {
                cardPin = view.getCardPin();
                for (char c : cardPin.toCharArray()) {
                    if (!Character.isDigit(c)) {
                        throw new InputMismatchException("Invalid Character: " + c);
                    }
                }
                if (cardPin.isEmpty()) {
                    throw new InputMismatchException(
                        "Card pin cannot be empty");
                }
                if (cardPin.length() != 4) {
                    throw new InputMismatchException("Card number must be comprised of 4 digits");
                }
                break;
            } catch (InputMismatchException e) {
                view.displayBody(e.getMessage(), "yellow");
            }
        }
        return Integer.parseInt(cardPin);
    }

    @Override
    public boolean isCardLock() {
        return atmCard.getStatus().equals("locked");
    }

    @Override
    public void handleClientCardLock() {
        boolean userRequestUnlock = getUserRequestUnlock();
        if (userRequestUnlock) {
            logEvent(atmCard, EnumEventNames.UNLOCK_CARD_REQUEST);
        }
        view.displayBody("Thank you for using the AtmApp! Good Bye.");
        setAtmCard(null);
    }

    private boolean getUserRequestUnlock() {
        while (true) {
            String input = view.displayUserRequestUnlock();
            if (input.equals("1")) {
                view.displayBody("Card Unlock Requested.");
                return true;
            } else if (input.equals("2")) {
                setAtmCard(null);
                return false;
            } else {
                view.displayBody("Invalid input! Please select choice 1 or 2. ");
            }
        }
    }

    @Override
    public void setAtmCard(Card card) {
        this.atmCard = card;
    }

    @Override
    public Card getAtmCard() {
        return atmCard;
    }

}
