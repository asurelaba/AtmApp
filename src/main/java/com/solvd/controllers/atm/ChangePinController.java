package com.solvd.controllers.atm;

import com.solvd.controllers.icontrollers.IFeatureController;
import com.solvd.db.model.Card;
import com.solvd.db.model.Event;
import com.solvd.services.CardService;
import com.solvd.services.EventService;
import com.solvd.services.EventTypeService;
import com.solvd.views.atm.ChangePinView;

import java.sql.Timestamp;
import java.time.Instant;

public class ChangePinController implements IFeatureController {

    protected Card card;
    ChangePinView changePinView = new ChangePinView();

    public ChangePinController(Card card) {
        this.card = card;
    }

    @Override
    public void run() {
        changePinView.displayTitle(changePinView.featureTitle());
        handleChangePin();
        do {
            changePinView.displayExit();
        } while (changePinView.getUserSelection() != 0);
    }

    private void handleChangePin() {
        int newPin = changePinView.getNewCardPin();
        while (newPin == card.getPin()) {
            changePinView.displayBody("New pin is same as old Pin. Please enter a new Pin");
            newPin = changePinView.getNewCardPin();
        }
        card.setPin(newPin);
        new CardService().update(card);
        changePinView.displayBody("Pin changed successfully");
    }
}
