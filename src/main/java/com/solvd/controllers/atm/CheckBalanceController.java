package com.solvd.controllers.atm;

import com.solvd.EnumEventNames;
import com.solvd.controllers.icontrollers.IFeatureController;
import com.solvd.db.model.Account;
import com.solvd.db.model.Card;
import com.solvd.db.model.Event;
import com.solvd.db.model.Transaction;
import com.solvd.services.AccountService;
import com.solvd.services.TransactionService;
import com.solvd.views.CheckBalanceView;

public class CheckBalanceController implements IFeatureController {

    private final Card clientCard;

    public CheckBalanceController(Card clientCard) {
        this.clientCard = clientCard;
    }

    private final CheckBalanceView view = new CheckBalanceView();

    @Override
    public void run() {
        view.displayTitle(view.featureTitle());

        double clientBalance = getAccountBalance();
        String cardNum = String.valueOf(clientCard.getCardNumber());
        String lastFour = cardNum.substring(cardNum.length() - 4);

        view.displayBalance(clientBalance, lastFour);
        Event event = logEvent(clientCard, EnumEventNames.BALANCE_INQUIRY);

        Transaction transaction = new Transaction();
        transaction.setStatus("approved");
        transaction.setEvent(event);
        new TransactionService().insert(transaction);

        promptPrintReceipt(view, transaction);
    }

    private double getAccountBalance() {
        AccountService as = new AccountService();
        Account clientAccount = as.getAccountByUserId(clientCard.getUser().getUserId());
        return clientAccount.getBalance();
    }

}
