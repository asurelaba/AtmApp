package com.solvd.controllers.atm.cardsmenu;

import com.solvd.EnumEventNames;
import com.solvd.controllers.icontrollers.IFeatureController;
import com.solvd.db.model.Card;
import com.solvd.services.CardService;
import com.solvd.views.atm.CardsView;
import java.util.function.Supplier;

public class CardsController implements IFeatureController {

    private final Card adminCard;
    private final CardService cs = new CardService();
    private final CardsView view = new CardsView();

    public CardsController(Card adminCard) {
        this.adminCard = adminCard;
    }

    @Override
    public void run() {
        while (true) {
            view.displayCardsMenuSelection();
            int menuChoice = view.getUserSelection();

            switch (menuChoice) {
                case 1 -> handleAddCard(adminCard);
                case 2 -> handleDeleteCard(adminCard);
                case 3 -> handleLockCard(adminCard);
                case 4 -> handleUnlockCard(adminCard);
                case 5 -> handleViewAllCards();
                case 6 -> {
                    view.display("Returning to the main menu...");
                    return;
                }
                default -> view.display("Invalid choice. Please try again.");
            }
        }
    }

    private void handleAddCard(Card adminCard) {
        new AddCardsController(adminCard).run();
    }

    private void handleDeleteCard(Card adminCard) {
        while (true) {
            Supplier<String> getCardNumInput = view::getCardNumberToDelete;
            long userInputCardNum = cardNumberValidator(view, getCardNumInput);
            Card cardToDelete = cs.getCardByCardNumber(userInputCardNum);
            if (cardToDelete != null) {
                cs.delete(cardToDelete.getCardId());
                logEvent(adminCard, EnumEventNames.CARD_REMOVAL);
                break;
            }
            view.displayBody("Card Number not in database. Enter a valid card number.", "yellow");
        }
    }

    private void handleLockCard(Card adminCard) {
        // "Lock Card"
    }

    private void handleUnlockCard(Card adminCard) {
        // "Unlock Card" via "Unlock Card Request"
    }

    private void handleViewAllCards() {
        // "Cards Query"
    }

}
