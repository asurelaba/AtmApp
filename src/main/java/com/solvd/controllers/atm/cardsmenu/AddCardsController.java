package com.solvd.controllers.atm.cardsmenu;

import com.solvd.EnumEventNames;
import com.solvd.controllers.icontrollers.IFeatureController;
import com.solvd.db.model.Card;
import com.solvd.db.model.CardType;
import com.solvd.db.model.User;
import com.solvd.services.CardService;
import com.solvd.services.CardTypeService;
import com.solvd.services.UserService;
import com.solvd.views.atm.CardsView;
import java.util.Random;

public class AddCardsController implements IFeatureController {

    private final Card adminCard;
    private Card cardToAdd;
    private final String INITIAL_CARD_STATUS = "active";

    CardsView view = new CardsView();

    public AddCardsController(Card adminCard) {
        this.adminCard = adminCard;
    }

    @Override
    public void run() {
        cardToAdd = new Card();
        User user = handleUser();
        cardToAdd.setUser(user);
        handleSetCardType(user);
        cardToAdd.setStatus(INITIAL_CARD_STATUS);
        cardToAdd.setCardNumber(generateCardNumber());
        cardToAdd.setPin(generatePinNumber());

        new CardService().insert(cardToAdd);
        view.displayBody("New card created.");
        view.displayCardInformation(cardToAdd);

        logEvent(adminCard, EnumEventNames.CARD_CREATION);
        exitRun(view);
    }

    private User handleUser() {
        User user;
        while (true) {
            int userIdInput = getUserFromInput();
            if (validateUserIdInput(userIdInput)) {
                user = new UserService().getById(userIdInput);
                break;
            } else {
                view.displayBody("Invalid userId. Please enter a valid user ID.", "yellow");
            }
        }
        return user;
    }

    private boolean validateUserIdInput(int userIdInput) {
        return new UserService().getById(userIdInput) != null;
    }

    private int getUserFromInput() {
        String intStr = null;
        while (true) {
            view.displayBody("Please enter either a client's or administrator's User ID.");
            intStr = view.getUserIdInput();
            try {
                Integer.parseInt(intStr);
                break;
            } catch (NumberFormatException e) {
                view.displayBody(
                    "Invalid input", "yellow");
            }
        }
        return Integer.parseInt(intStr);
    }

    private void handleSetCardType(User user) {
        String cardTypeAdminInput = getCardTypeFromUser(user);
        CardType cardType = new CardTypeService().getCardTypeByName(cardTypeAdminInput);
        cardToAdd.setCardType(cardType);
    }

    private String getCardTypeFromUser(User user) {
        String cardType = null;
        switch (user.getUserRole().getName()) {
            case "Administrator" -> cardType = "AdministratorCard";
            case "Client" -> cardType = "ClientCard";
            default -> view.displayBody("Invalid selection", "yellow");
        }
        return cardType;
    }

    private long generateCardNumber() {
        boolean isCardNumValid = false;
        long genCardNum = 0;
        while (!isCardNumValid) {
            StringBuilder sb = new StringBuilder();
            Random random = new Random();
            int cardLen = 16;
            int firstDigit = random.nextInt(9) + 1; // first number cannot be 0
            sb.append(firstDigit);
            for (int i = 1; i < cardLen; i++) {
                int digit = random.nextInt(10);
                sb.append(digit);
            }
            genCardNum = Long.parseLong(sb.toString());
            isCardNumValid = new CardService().getCardByCardNumber(genCardNum) == null;
        }
        return genCardNum;
    }

    private int generatePinNumber() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int firstDigit = random.nextInt(9) + 1; // first number cannot be 0
        sb.append(firstDigit);
        int pinLen = 4;
        for (int i = 1; i < pinLen; i++) {
            int digit = random.nextInt(10);
            sb.append(digit);
        }
        return Integer.parseInt(sb.toString());
    }

}
