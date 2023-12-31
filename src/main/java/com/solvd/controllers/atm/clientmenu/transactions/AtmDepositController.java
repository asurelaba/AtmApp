package com.solvd.controllers.atm.clientmenu.transactions;

import com.solvd.db.model.Card;
import com.solvd.enums.EnumEventName;

public class AtmDepositController extends AbstractTransactionController {

    public AtmDepositController(Card clientCard) {
        super(clientCard);
    }

    @Override
    public void getTransactionAmount() {
        while (true) {
            amount = view.getTransactionAmount();
            if (checkBalance() == 9999999999d) {
                view.displayBody("The account balance has reached the maximum limit.");
                exitRun(view);
                break;
            } else if ((checkBalance() + amount) > 9999999999d) {
                view.displayBody(
                    "Invalid transaction amount. The account will reach its maximum balance " +
                        "with this amount. Enter a smaller amount.");
            } else {
                run();
                break;
            }
        }
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
