package com.solvd.controllers.icontrollers.atm;

import com.solvd.db.model.Card;
import com.solvd.controllers.icontrollers.IFeatureController;

public interface IAtmLoginController extends IFeatureController {

    void handleCardNumberInput();

    boolean handlePinNumberInput();

    boolean isCardLock();

    void handleClientCardLock();

    void setAtmUserByCardNumber(long cardNum);

    Card getAtmCard();

    void setAtmCard(Card card);

}
