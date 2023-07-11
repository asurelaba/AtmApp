package com.solvd.controllers.atm;

import com.solvd.EnumEventNames;
import com.solvd.db.model.Card;

public class AtmDepositController extends AbstractTransactionController {

    public AtmDepositController(Card clientCard) {
        super(clientCard);
    }

    @Override
    public void getTransactionAmount() {
        while (true) {
            amount = view.getTransactionAmount();
            if ((checkBalance() + amount) > 9999999999d) {
                view.displayBody("Invalid transaction amount. Enter a smaller amount.");
            } else {
                break;
            }
        }

        run();
    }


    @Override
    public void updateBalance() {
        account.setBalance(checkBalance() + amount);
        accountService.update(account);
    }

    @Override
    public EnumEventNames getEventType() {
        return EnumEventNames.DEPOSIT;
    }

}
