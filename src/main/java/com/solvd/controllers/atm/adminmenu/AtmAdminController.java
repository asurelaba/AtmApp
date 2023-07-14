package com.solvd.controllers.atm.adminmenu;

import com.solvd.controllers.atm.AtmController;
import com.solvd.controllers.atm.ChangePinController;
import com.solvd.controllers.atm.adminmenu.cardsmenu.CardsController;
import com.solvd.controllers.icontrollers.atm.IAtmAdminController;
import com.solvd.db.model.Card;
import com.solvd.db.model.User;
import com.solvd.views.atm.admin.AtmAdminView;

public class AtmAdminController implements IAtmAdminController {

    protected Card adminCard;
    AtmAdminView view = new AtmAdminView();

    public AtmAdminController(Card adminCard) {
        this.adminCard = adminCard;
    }

    @Override
    public void run() {
        while (true) {
            view.displayGreeting(adminCard.getUser());
            view.displayAdminView();
            int adminInput = view.getUserSelection();
            switch (adminInput) {
                case 1 -> handleAddDeleteViewUsers();
                case 2 -> handleTransactionQueryMenu();
                case 3 -> handleClientAccounts();
                case 4 -> handleCards();
                case 5 -> handleChangePin();
                case 6 -> {
                    handleLogout();
                    return;
                }
                case 7 -> {
                    handleAppShutdown();
                    return;
                }
                default -> view.display("Invalid selection");
            }
        }

    }

    @Override
    public void handleTransactionQueryMenu() {
        new AdminTransactionController(adminCard).run();
    }

    @Override
    public void handleCards() {
        new CardsController(adminCard).run();
    }

    @Override
    public void handleClientAccounts() {
        new AdminClientAccountController(adminCard).run();
    }

    @Override
    public void handleChangePin() {
        new ChangePinController(adminCard).run();
    }

    @Override
    public void handleLogout() {
        User admin = adminCard.getUser();
        view.display("Goodbye, " + admin.getPerson().getFirstName());
        view.display("Logging out");
    }

    @Override
    public void handleAppShutdown() {
        handleLogout();
        AtmController.isRunning = false;
    }

    @Override
    public void handleAddDeleteViewUsers() {
        new AddDeleteViewUserController(adminCard).run();
    }

}
