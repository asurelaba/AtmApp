package com.solvd.controllers.icontrollers.atm;

import com.solvd.controllers.icontrollers.IFeatureController;
import com.solvd.db.model.Card;

public interface IAtmLoginController extends IFeatureController {

    void handleCardNumberInput();

    boolean handlePinNumberInput();

    boolean isCardLock();

    void handleClientCardLock();

    Card getAtmCard();

    void setAtmCard(Card card);

}
