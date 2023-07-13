package com.solvd.views.atm;

import com.solvd.db.model.Card;
import com.solvd.db.model.Person;

public class CardsView extends AbstractAtmView {

    @Override
    public String featureTitle() {
        return "Cards Menu";
    }

    public void displayCardsMenuSelection() {
        displayBody("1. Add Card");
        displayBody("2. Delete Card");
        displayBody("3. Lock Card");
        displayBody("4. Unlock Card");
        displayBody("5. View All Cards");
        displayBody("6. Return To Main Menu");
    }

    public String getUserIdInput() {
        return s.next();
    }

    public void displayCardInformation(Card cardToAdd) {
        Person p = cardToAdd.getUser().getPerson();
        displayBody("CARD NUMBER: " + cardToAdd.getCardNumber());
        displayBody("PIN NUMBER: " + cardToAdd.getPin());
        displayBody("CARD TYPE: " + cardToAdd.getCardType().getName());
        displayBody("CARD OWNER: " + p.getFirstName() + " " + p.getLastName());
    }

    public String getCardNumberToDelete() {
        displayBody("Enter the card number to delete: ");
        return s.next().trim();
    }

}
