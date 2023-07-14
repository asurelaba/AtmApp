package com.solvd.views.atm.admin;

import com.solvd.db.model.Card;
import com.solvd.db.model.Person;
import com.solvd.views.atm.AbstractAtmView;
import java.util.List;

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
        displayBody("0. Return To Main Menu");
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

    public String getCardNumberToLock() {
        displayBody("Enter the card number to lock: ");
        return s.next().trim();
    }

    public void displayReqUnlockCards(List<Card> cards) {
        int screenWidth = 100;
        int columnWidth = screenWidth / 4;
        display("-".repeat(screenWidth));
        display("|" +
            centerAndTrim("CARD ID", columnWidth) + "|" +
            centerAndTrim("CARD NUMBER", columnWidth) + "|" +
            centerAndTrim("NAME", columnWidth) + "|" +
            centerAndTrim("STATUS", columnWidth) + "|"
        );
        display("-".repeat(screenWidth));
        for (Card card : cards) {
            Person p = card.getUser().getPerson();
            String name = p.getFirstName() + " " + p.getLastName();
            display("|" +
                centerAndTrim(String.valueOf(card.getCardId()), columnWidth) + "|" +
                centerAndTrim(String.valueOf(card.getCardNumber()), columnWidth) + "|" +
                centerAndTrim(name, columnWidth) + "|" +
                centerAndTrim(card.getStatus(), columnWidth) + "|"
            );
        }
    }

    public void displayCards(List<Card> cards) {
        int screenWidth = 110;
        int columnWidth = screenWidth / 6;
        display("-".repeat(screenWidth));
        display("|" +
            centerAndTrim("CARD ID", columnWidth) + "|" +
            centerAndTrim("CARD NUMBER", columnWidth) + "|" +
            centerAndTrim("CARD PIN", columnWidth) + "|" +
            centerAndTrim("STATUS", columnWidth) + "|" +
            centerAndTrim("TYPE", columnWidth) + "|" +
            centerAndTrim("NAME", columnWidth) + "|"
        );
        display("-".repeat(screenWidth));
        for (Card card : cards) {
            Person p = card.getUser().getPerson();
            String name = p.getFirstName() + " " + p.getLastName();
            display("|" +
                centerAndTrim(String.valueOf(card.getCardId()), columnWidth) + "|" +
                centerAndTrim(String.valueOf(card.getCardNumber()), columnWidth) + "|" +
                centerAndTrim(String.valueOf(card.getPin()), columnWidth) + "|" +
                centerAndTrim(card.getStatus(), columnWidth) + "|" +
                centerAndTrim(card.getCardType().getName(), columnWidth) + "|" +
                centerAndTrim(name, columnWidth) + "|"
            );
        }
    }

    public String getUserInputCardUnlock() {
        return s.nextLine();
    }

}
