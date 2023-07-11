package com.solvd.controllers.atm;

import com.solvd.EnumEventNames;
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
        while (!loggedIn || atmCard == null ) {
            handleCardNumberInput();
            if (atmCard == null) {
                view.display("Invalid Card Number.", "yellow");
                continue; // asks for cardNumber again;
            }

            if (isCardLock()) {
                view.display("Card is locked. Enter correct pin to request unlock", "yellow");
                if (handlePinNumberInput()){
                    handleClientCardLock();
                }else{
                    view.display("Incorrect pin. Goodbye.", "red");
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
            view.display("2 more attempts remaining.", "yellow");
        } else if (pinAttempts == MAX_PIN_ATTEMPTS - 1) {
            view.display("1 more attempt remaining.", "yellow");
        } else if (pinAttempts == MAX_PIN_ATTEMPTS) {
            atmCard.setStatus("locked");
            new CardService().update(atmCard);
            view.display("Too many login attempts. Locking card.. Goodbye.", "red");
            setAtmCard(null);
        }
    }

    @Override
    public void handleCardNumberInput() {
        long userInputCardNum = view.getCardNumber();
        CardService cs = new CardService();
        Card atmCard = cs.getCardByCardNumber(userInputCardNum);
        setAtmCard(atmCard);
    }

    public boolean handlePinNumberInput() {
        int cardPin = view.getCardPin();
        return cardPin == atmCard.getPin();
    }

    @Override
    public boolean isCardLock() {
        return atmCard.getStatus().equals("locked");
    }

    @Override
    public void handleClientCardLock() {
        view.displayCardLocked();
        boolean userRequestUnlock = view.displayUserRequestUnlock();
        if (userRequestUnlock) {
            logEvent(atmCard, EnumEventNames.UNLOCK_CARD_REQUEST);
        }
        setAtmCard(null);
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
