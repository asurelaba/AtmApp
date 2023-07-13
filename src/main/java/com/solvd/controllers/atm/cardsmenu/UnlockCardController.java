package com.solvd.controllers.atm.cardsmenu;

import com.solvd.EnumEventNames;
import com.solvd.controllers.icontrollers.IFeatureController;
import com.solvd.db.model.Card;
import com.solvd.services.CardService;
import com.solvd.views.atm.CardsView;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UnlockCardController implements IFeatureController {

    private final Card adminCard;
    private final CardsView view = new CardsView();
    private final CardService cs = new CardService();

    public UnlockCardController(Card adminCard) {
        this.adminCard = adminCard;
    }

    @Override
    public void run() {
        view.displayTitle("Card Unlock Requests");

        List<Card> cardsReqToUnlock = cs.getCardsByCardStatus("locked");
        if (cardsReqToUnlock.isEmpty()) {
            view.displayBody("There are no card unlock requests.");
        } else {
            view.displayReqUnlockCards(cardsReqToUnlock);
            handleUserInputCardUnlock(cardsReqToUnlock);
        }

        exitRun(view);
    }

    private void handleUserInputCardUnlock(List<Card> cardsReqToUnlock) {
        view.displayBody("Enter card id(s) to unlock card or type 'all'.");
        String userInput = view.getUserInputCardUnlock();
        String[] tokens = userInput.split("[,\\s]+"); // split by space or comma

        List<String> cardIds = cardsReqToUnlock.stream()
            .map(Card::getCardId)
            .map(String::valueOf)
            .collect(Collectors.toList());

        Set<String> validTokens = new HashSet<>();
        for (String token : tokens) {
            if (cardIds.contains(token) || token.equals("all")) {
                validTokens.add(token);
            }
        }

        if (validTokens.contains("all")) {
            cardIds.forEach(cardIdStr -> {
                int cardId = Integer.parseInt(cardIdStr);
                Card card = cs.getById(cardId);
                card.setStatus("active");
                cs.update(card);
                logEvent(adminCard, EnumEventNames.UNLOCK_CARD);
            });
            view.displayBody(cardIds.size() + " card(s) unlocked: " + String.join(", ", cardIds));
        } else {
            validTokens.forEach(cardIdStrToken -> {
                int cardId = Integer.parseInt(cardIdStrToken);
                Card card = cs.getById(cardId);
                card.setStatus("active");
                cs.update(card);
                logEvent(adminCard, EnumEventNames.UNLOCK_CARD);
            });
            view.displayBody(
                validTokens.size() + " card(s) unlocked: " + String.join(", ", validTokens));
        }

    }

}
