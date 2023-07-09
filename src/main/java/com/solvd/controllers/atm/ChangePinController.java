package com.solvd.controllers.atm;

import com.solvd.controllers.icontrollers.IFeatureController;
import com.solvd.db.model.Card;
import com.solvd.views.atm.ChangePinView;

public class ChangePinController implements IFeatureController {

    protected Card adminCard;
    ChangePinView changePinView = new ChangePinView();

    public ChangePinController(Card adminCard) {
        this.adminCard = adminCard;
    }

    @Override
    public void run() {
        changePinView.displ
    }
}
