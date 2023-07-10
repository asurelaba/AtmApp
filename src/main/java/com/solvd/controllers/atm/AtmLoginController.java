package com.solvd.controllers.atm;

import com.solvd.controllers.icontrollers.atm.IAtmLoginController;
import com.solvd.db.model.Card;
import com.solvd.db.model.Event;
import com.solvd.services.CardService;
import com.solvd.services.EventService;
import com.solvd.services.EventTypeService;
import com.solvd.views.atm.AtmLoginView;
import java.sql.Timestamp;
import java.time.Instant;

public class AtmLoginController implements IAtmLoginController {

    protected Card atmCard;

    private final AtmLoginView view = new AtmLoginView();

    @Override
    public void run() {
        boolean loggedIn = false;
        while (!loggedIn) {
            handleCardNumberInput();
            if (atmCard == null) {
                view.display("Invalid Card Number.");
                continue; // asks for cardNumber again;
            }
            if (!handlePinNumberInput()) {
                view.display("Invalid Card PIN.");
                continue; //
            }
            if (isCardLock()) {
                handleClientCardLock();
                continue;
            }
            loggedIn = true;
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
            Event event = new Event();
            event.setCard(atmCard);
            event.setDatetime(Timestamp.from(Instant.now()));
            event.setEventType(
                new EventTypeService().getEventTypeByTypeName("Unlock Card Request"));
            new EventService().insert(event);
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