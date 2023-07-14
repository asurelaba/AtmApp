package com.solvd.controllers.atm;

import com.solvd.controllers.atm.adminmenu.AtmAdminController;
import com.solvd.controllers.atm.clientmenu.AtmClientController;
import com.solvd.controllers.icontrollers.atm.IAtmController;
import com.solvd.db.model.Card;
import com.solvd.enums.EnumEventName;
import com.solvd.views.atm.AtmView;
import org.apache.ibatis.exceptions.PersistenceException;


public class AtmController implements IAtmController {

    private static final AtmView view = new AtmView();
    public static boolean isRunning = true;

    public void run() {
        while (isRunning) {
            view.displayWelcome();
            try {
                AtmLoginController atmLoginController = new AtmLoginController();
                atmLoginController.run();

                Card atmCard = atmLoginController.getAtmCard();
                switch (atmCard.getCardType().getName()) {
                    case "ClientCard" -> new AtmClientController(atmCard).run();
                    case "AdministratorCard" -> new AtmAdminController(atmCard).run();
                }
                logEvent(atmCard, EnumEventName.LOG_OUT);
                atmReset();
            } catch (PersistenceException e) {
                view.displayBody("Unable to connect to database. Please come back later." ,"red");
            }
        }
        view.display("Shutting Down..");
        System.exit(0);
    }

    @Override
    public void atmReset() {
        view.displayAtmReset();
    }

}
