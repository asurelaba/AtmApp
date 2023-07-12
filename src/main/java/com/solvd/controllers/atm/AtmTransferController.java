package com.solvd.controllers.atm;

import com.solvd.enums.EnumEventNames;
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

            if (userSel == 1) {
                exitRun(view);
            } else {
                getRecipientAccountId();
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

}
