package com.solvd.controllers.atm;

import com.solvd.controllers.atm.cardsmenu.CardsController;
import com.solvd.controllers.icontrollers.atm.IAtmAdminController;
import com.solvd.db.model.Card;
import com.solvd.db.model.User;
import com.solvd.views.atm.AtmAdminView;

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
                case 1 -> handleCreateUser();
                case 2 -> handleUnlockCardRequests();
                case 3 -> handleLockUserCard();
                case 4 -> handleTransactionQueryMenu();
                case 5 -> placeHolder();
                case 6 -> placeHolder();
                case 7 -> handleChangePin();
                case 8 -> {
                    handleLogout();
                    return;
                }
                case 9 -> {
                    handleAppShutdown();
                    return;
                }
                default -> view.display("Invalid selection");
            }
        }

    }

    public void handleTransactionQueryMenu() {
        new AdminTransactionController(adminCard).run();
    }

    public void placeHolder() {
        view.display("placeholder");
    }

    @Override
    public void handleCreateUser() {
        // TODO
    }

    @Override
    public void handleUnlockCardRequests() {
        // TODO
    }

    @Override
    public void handleLockUserCard() {
        // TODO
    }

    public void handleChangePin() {
        new ChangePinController(adminCard).run();
    }

    @Override
    public void handleLogout() {
        User admin = adminCard.getUser();
        view.display("Goodbye, " + admin.getPerson().getFirstName());
        view.display("Logging out");
    }

    private void handleAppShutdown() {
        handleLogout();
        AtmController.isRunning = false;
    }

}
