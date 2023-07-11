package com.solvd.controllers.atm;

import com.solvd.enums.EnumEventName;
import com.solvd.db.model.Card;

public class AtmDepositController extends AbstractTransactionController {

    public AtmDepositController(Card clientCard) {
        super(clientCard);
    }

    @Override
    public void getTransactionAmount() {
        amount = view.getTransactionAmount();
        run();
    }

    @Override
    public void updateBalance() {
        account.setBalance(checkBalance() + amount);
        accountService.update(account);
    }

    @Override
    public EnumEventName getEventType() {
        return EnumEventName.DEPOSIT;
    }

}
