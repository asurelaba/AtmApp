package com.solvd.controllers.atm;

import com.solvd.controllers.ExampleFeatureController;
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
                case 1 -> handleAddDeleteViewUsers();
                case 2 -> handleUnlockCardRequests();
                case 3 -> handleLockUserCard();
                case 4 -> handleClientAccounts();
                case 5 -> handleModifyCards();
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

    public void placeHolder() {
        view.display("placeholder");
        new ExampleFeatureController(adminCard).run();
    }

    public void handleModifyCards() {
        new CardsController(adminCard).run();
    }

    @Override
    public void handleUnlockCardRequests() {
        // TODO
    }

    public void handleClientAccounts() {
        new AdminClientAccountController(adminCard).run();
    }

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

    @Override
    public void handleAddDeleteViewUsers() {
        new AddDeleteViewUserController(adminCard).run();
    }
}
