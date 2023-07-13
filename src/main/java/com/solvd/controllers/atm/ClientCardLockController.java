package com.solvd.controllers.atm;

import com.solvd.enums.EnumEventNames;
import com.solvd.controllers.icontrollers.IFeatureController;
import com.solvd.db.model.Card;
import com.solvd.services.CardService;
import com.solvd.views.atm.ClientCardLockView;

public class ClientCardLockController implements IFeatureController {

    Card card;
    ClientCardLockView view = new ClientCardLockView();

    public ClientCardLockController(Card card) {
        this.card = card;
    }

    @Override
    public void run() {
        view.displayTitle(view.featureTitle());
    }

    public boolean handleLockRequest() {
        view.displayTitle(view.featureTitle());
        view.displayBody("You are locking card number ending " + String.valueOf(card.getCardNumber()).substring(12));
        int choice;
        do {
            view.displayBody("Please confirm 1. Yes / 2. No");
            choice = view.getUserSelection();
        } while (choice < 1 || choice > 2);
        if (choice == 1) {
            card.setStatus("locked");
            new CardService().update(card);
            logEvent(card, EnumEventNames.LOCK_CARD);
            view.displayBody("Card is locked. Logging out...");
            return true;
        }
        return false;
    }
}
