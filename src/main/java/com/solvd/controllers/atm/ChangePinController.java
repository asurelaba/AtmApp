package com.solvd.controllers.atm;

import com.solvd.enums.EnumEventNames;
import com.solvd.controllers.icontrollers.IFeatureController;
import com.solvd.db.model.Card;
import com.solvd.services.CardService;
import com.solvd.views.atm.ChangePinView;

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
        exitRun(changePinView);
    }

    private void handleChangePin() {
        int newPin = changePinView.getNewCardPin();
        while (newPin == card.getPin()) {
            changePinView.displayBody("New pin is same as old Pin. Please enter a new Pin");
            newPin = changePinView.getNewCardPin();
        }
        card.setPin(newPin);
        new CardService().update(card);
        logEvent(card, EnumEventNames.CHANGE_PIN);
        changePinView.displayBody("Pin changed successfully");
    }
}
