package com.solvd.controllers.atm;

import com.solvd.db.model.Card;
import com.solvd.controllers.icontrollers.atm.IAtmController;
import com.solvd.views.atm.AtmView;


public class AtmController implements IAtmController {

    private static final AtmView view = new AtmView();
    protected static boolean isRunning = true;

    public void run() {
        while (isRunning) {
            view.displayWelcome();
            AtmLoginController atmLoginController = new AtmLoginController();
            atmLoginController.run();

            Card atmCard = atmLoginController.getAtmCard();
            switch (atmCard.getCardType().getName()) {
                case "ClientCard" -> new AtmClientController(atmCard).run();
                case "AdministratorCard" -> new AtmAdminController(atmCard).run();
            }
            atmReset();
        }
        view.display("Shutting Down..");
        System.exit(0);
    }

    @Override
    public void atmReset() {
        view.displayAtmReset();
    }

}