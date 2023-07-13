package com.solvd.controllers.atm;

import com.solvd.EnumEventNames;
import com.solvd.controllers.icontrollers.atm.IAtmTransferController;
import com.solvd.db.model.Account;
import com.solvd.db.model.Card;

public class AtmTransferController extends AbstractTransactionController implements IAtmTransferController {

    private int recipientAccountId;

    public AtmTransferController(Card clientCard) {
        super(clientCard);
    }

    @Override
    public void getRecipientAccountId() {
        recipientAccountId = view.getAccountId();
        validateRecipientAccount();
    }

    @Override
    public void validateRecipientAccount() {
        if (accountService.getById(recipientAccountId) != null) {
            getTransactionAmount();
        } else {
            view.displayNonexistentAccountChoices();
            int userSel = view.getUserChoice();

            if (userSel == 2) {
                getRecipientAccountId();
            }
        }
    }

    @Override
    public void getTransactionAmount() {
        boolean validTransfer = false;

        while (!validTransfer) {
            amount = view.getTransactionAmount();

            double balance = checkBalance();
            if (amount <= balance) {
                if (validateAmount(amount)) {
                    run();
                    validTransfer = true;
                } else {
                    return;
                }
            } else {
                view.display("Insufficient balance.");
                if (handleInsufficientBalance() == 1) {
                    return;
                }
            }
        }
    }

    @Override
    public void updateBalance() {
        account.setBalance(checkBalance() - amount);
        accountService.update(account);

        Account recipientAccount = accountService.getById(recipientAccountId);
        recipientAccount.setBalance(recipientAccount.getBalance() + amount);
        accountService.update(recipientAccount);
    }

    @Override
    public EnumEventNames getEventType() {
        return EnumEventNames.TRANSFER;
    }

    @Override
    public boolean validateAmount(double amount) {
        Account recipientAccount = accountService.getById(recipientAccountId);
        if (recipientAccount.getBalance() == 9999999999d || (recipientAccount.getBalance() + amount) > 9999999999d) {
            view.displayBody("Transfer to this recipient is currently unavailable. " +
                    "Please contact the branch office for assistance.");
            exitRun(view);

            return false;
        }

        return true;
    }

}
