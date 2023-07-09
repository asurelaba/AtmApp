package com.solvd.controllers.atm;

import com.solvd.db.model.Card;

public class AtmDepositController extends AbstractTransactionController {

    public AtmDepositController(Card clientCard) {
        super(clientCard);
    }

    @Override
    public void getTransactionAmount() {
        amount = view.getTransactionAmount();
    }

    @Override
    public void updateBalance() {
        account.setBalance(checkBalance() + amount);
        accountService.update(account);
    }

    @Override
    public void setEventType() {
        eventType = "Deposit";
    }
}
