package com.solvd.controllers.atm;

import com.solvd.controllers.icontrollers.atm.IAtmClientController;
import com.solvd.db.model.Card;
import com.solvd.db.model.User;
import com.solvd.views.atm.AtmClientView;

public class AtmClientController implements IAtmClientController {

    protected Card clientCard;

    private final AtmClientView view = new AtmClientView();

    public AtmClientController(Card clientCard) {
        this.clientCard = clientCard;
    }

    @Override
    public void run() {
        while (true) {
            view.displayGreeting(clientCard.getUser());
            view.displayClientMenu();
            int clientInput = view.getUserSelection();

            switch (clientInput) {
                case 1 -> handleWithdraw();
                case 2 -> handleDeposit();
                case 3 -> handleCheckBalance();
                case 4 -> handleLockCardRequest();
                case 5 -> handleChangePin();
                case 6 -> {
                    handleLogout();
                    return;
                }
                default -> {
                    view.display("Invalid selection");
                }
            }
        }
    }

    @Override
    public void handleWithdraw() {
        // TODO
    }

    @Override
    public void handleDeposit() {
        // TODO
    }

    @Override
    public void handleCheckBalance() {
        new CheckBalanceController(clientCard).run();
    }

    @Override
    public void handleLockCardRequest() {
        // TODO
    }

    @Override
    public void handleChangePin() {
        new ChangePinController(clientCard).run();
    }

    @Override
    public void handleLogout() {
        User client = clientCard.getUser();
        view.display("Goodbye, " + client.getPerson().getFirstName());
        view.display("Logging out");
    }

}
