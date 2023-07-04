package com.solvd.controllers.atm;

import com.solvd.db.model.User;
import com.solvd.views.atm.AtmView;


public class AtmController {

    private static final AtmView view = new AtmView();

    public void run() {
        view.display("Welcome to the AtmApp!");
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

    public void atmReset() {
        view.display("ATM Reset In Progress. Please Wait..");
        view.display(System.lineSeparator());
        run();
    }

}
