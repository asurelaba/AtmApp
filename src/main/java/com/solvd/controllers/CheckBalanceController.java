package com.solvd.controllers;

import com.solvd.controllers.icontrollers.IFeatureController;
import com.solvd.db.model.Account;
import com.solvd.db.model.Card;
import com.solvd.services.AccountService;
import com.solvd.views.CheckBalanceView;

public class CheckBalanceController extends AbstractFeatureController implements
    IFeatureController {

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
        logEvent(clientCard, "Check Balance");

        exitRun(view);
    }

    private double getAccountBalance() {
        AccountService as = new AccountService();
        Account clientAccount = as.getAccountByUserId(clientCard.getUser().getUserId());
        return clientAccount.getBalance();
    }

}
