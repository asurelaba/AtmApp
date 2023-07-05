package com.solvd.controllers.atm;

import com.solvd.controllers.ExampleFeatureController;
import com.solvd.db.model.User;
import com.solvd.interfaces.icontrollers.atm.IAtmAdminController;
import com.solvd.views.atm.AtmAdminView;

public class AtmAdminController implements IAtmAdminController {

    protected User admin;
    AtmAdminView view = new AtmAdminView();

    public AtmAdminController(User admin) {
        this.admin = admin;
    }

    @Override
    public void run() {
        while (true) {
            view.displayAdminView();
            int adminInput = view.getUserSelection();
            switch (adminInput) {
                case 1 -> handleCreateUser();
                case 2 -> handleUnlockCardRequests();
                case 3 -> handleLockUserCard();
                case 4 -> placeHolder();
                case 5 -> placeHolder();
                case 6 -> placeHolder();
                case 7 -> placeHolder();
                case 8 -> {
                    handleLogout();
                    return;
                }
                case 9 -> handleAppShutdown();
                default -> {
                    view.display("Invalid selection");
                }
            }
        }

    }

    private void handleAppShutdown() {
        System.exit(0);
    }

    public void placeHolder() {
        view.display("placeholder");
        new ExampleFeatureController(admin).run();
    }

    @Override
    public void handleCreateUser() {

    }

    @Override
    public void handleUnlockCardRequests() {

    }

    @Override
    public void handleLockUserCard() {

    }

    @Override
    public void handleLogout() {
        view.display("Goodbye, " + admin.getPerson().getFirstName());
        view.display("Logging out");
    }

}
