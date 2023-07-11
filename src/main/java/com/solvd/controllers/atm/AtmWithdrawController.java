package com.solvd.controllers.atm;

import com.solvd.enums.EnumEventName;
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
    public EnumEventName getEventType() {
        return EnumEventName.WITHDRAWAL;
    }

}
