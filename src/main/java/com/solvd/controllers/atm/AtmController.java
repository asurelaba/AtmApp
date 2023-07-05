package com.solvd.controllers.atm;

import com.solvd.db.model.User;
import com.solvd.views.atm.AtmView;


public class AtmController {

    private static final AtmView view = new AtmView();
    protected static boolean isRunning = true;

    public void run() {
        while (isRunning) {
            view.displayWelcome();
            AtmLoginController atmLoginController = new AtmLoginController();
            atmLoginController.run();

            String cardType = atmLoginController.getAtmCard().getCardType().getName();
            User atmUser = atmLoginController.getAtmUser();
            switch (cardType) {
                case "ClientCard" -> new AtmClientController(atmUser).run();
                case "AdministratorCard" -> new AtmAdminController(atmUser).run();
            }
            atmReset();
        }
        view.display("Shutting Down..");
        System.exit(0);
    }

    private void atmReset() {
        view.displayAtmReset();
    }

}
