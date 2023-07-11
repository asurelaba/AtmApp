package com.solvd.controllers.atm;

import com.solvd.EnumEventNames;
import com.solvd.db.model.Card;

public class AtmWithdrawController extends AbstractTransactionController {

    public AtmWithdrawController(Card clientCard) {
        super(clientCard);
    }

    @Override
    public void updateBalance() {
        account.setBalance(checkBalance() - amount);
        accountService.update(account);
    }

    @Override
    public EnumEventNames getEventType() {
        return EnumEventNames.WITHDRAWAL;
    }

}
