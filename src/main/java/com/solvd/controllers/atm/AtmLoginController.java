package com.solvd.controllers.atm;

import com.solvd.db.model.Card;
import com.solvd.db.model.CardType;
import com.solvd.db.model.Person;
import com.solvd.db.model.User;
import com.solvd.controllers.icontrollers.atm.IAtmLoginController;
import com.solvd.views.atm.AtmLoginView;

public class AtmLoginController implements IAtmLoginController {

    protected Card atmCard;

    private final AtmLoginView view = new AtmLoginView();

    //TODO remove after full handleCard implementations
    private Card getExampleClientCard() {
        Card exampleClientCard = new Card();
        CardType ct = new CardType();
        ct.setName("ClientCard");
        exampleClientCard.setCardNumber(1);
        exampleClientCard.setPin(11);
        exampleClientCard.setCardType(ct);
        exampleClientCard.setStatus("active");
        exampleClientCard.setUser(getExampleUser());
        return exampleClientCard;
    }

    //TODO remove after full handleCard implementations
    private Card getExampleAdminCard() {
        Card exampleAdminCard = new Card();
        CardType ct = new CardType();
        ct.setName("AdministratorCard");
        exampleAdminCard.setCardNumber(2);
        exampleAdminCard.setPin(22);
        exampleAdminCard.setCardType(ct);
        exampleAdminCard.setStatus("active");
        exampleAdminCard.setUser(getExampleUser());
        return exampleAdminCard;
    }

    //TODO remove after setAtmUserByCardNumber implementation
    private User getExampleUser() {
        User exampleUser = new User();
        Person examplePerson = new Person();
        examplePerson.setFirstName("exampleFirstName");
        exampleUser.setPerson(examplePerson);
        return exampleUser;
    }

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
            loggedIn = true;
        }

        if (isCardLock()) {
            handleClientCardLock();
        }

        setAtmUserByCardNumber(atmCard.getCardNumber());
    }

    @Override
    public void handleCardNumberInput() {
        long userInputCardNum = view.getCardNumber();

        //TODO CardController.validateCardNum(userInputCardNum) -> return atmCard = validCard else null
        Card exampleCard = atmCard;
        if (userInputCardNum == getExampleClientCard().getCardNumber()) {
            exampleCard = getExampleClientCard();
        } else if (userInputCardNum == getExampleAdminCard().getCardNumber()) {
            exampleCard = getExampleAdminCard();
        } else {
            exampleCard = null;
        }// TODO remove if-else block when validateCardNUm impl
        setAtmCard(exampleCard);
    }

    public boolean handlePinNumberInput() {
        int cardPin = view.getCardPin();

        // TODO CardController.validateCardPin(atmCard, cardPin) -> boolean
        return cardPin == atmCard.getPin();
    }

    @Override
    public boolean isCardLock() {
        return atmCard.getStatus().equals("locked");
    }

    @Override
    public void handleClientCardLock() {
        view.display("You card is locked.");
        view.display("Request unlock?");
        // TODO cardController.unlock
    }

    @Override
    public void setAtmUserByCardNumber(long cardNum) {
        // TODO: userController.getUserByCardNumber
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
