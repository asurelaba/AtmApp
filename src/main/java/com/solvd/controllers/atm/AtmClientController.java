package com.solvd.controllers.atm;

import com.solvd.controllers.icontrollers.atm.IAtmClientController;
import com.solvd.db.model.Card;
import com.solvd.db.model.User;
import com.solvd.views.atm.AtmClientView;

public class AtmClientController implements IAtmClientController {

    protected Card clientCard;

    private final AtmClientView view = new AtmClientView();
    private boolean cardLocked = false;

    public AtmClientController(Card clientCard) {
        this.clientCard = clientCard;
    }

    @Override
    public void run() {
        while (!cardLocked) {
            view.displayGreeting(clientCard.getUser());
            view.displayClientMenu();
            int clientInput = view.getUserSelection();

            switch (clientInput) {
                case 1 -> handleWithdraw();
                case 2 -> handleDeposit();
                case 3 -> handleTransfer();
                case 4 -> handleCheckBalance();
                case 5 -> handleLockCardRequest();
                case 6 -> handleChangePin();
                case 7 -> {
                    handleLogout();
                    return;
                }
                default -> view.display("Invalid selection");
            }
        }
    }

    @Override
    public void handleWithdraw() {
        new AtmWithdrawController(clientCard).getTransactionAmount();
    }

    @Override
    public void handleDeposit() {
        new AtmDepositController(clientCard).getTransactionAmount();
    }

    @Override
    public void handleTransfer() {
        new AtmTransferController(clientCard).getRecipientAccountId();
    }

    @Override
    public void handleCheckBalance() {
        new CheckBalanceController(clientCard).run();
    }

    @Override
    public void handleLockCardRequest() {
        ClientCardLockController clientCardLockController = new ClientCardLockController(clientCard);
        cardLocked = clientCardLockController.handleLockRequest();
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
