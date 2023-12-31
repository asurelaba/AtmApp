package com.solvd.controllers.atm.clientmenu.transactions;

import com.solvd.controllers.icontrollers.atm.IAtmTransactionController;
import com.solvd.db.model.Account;
import com.solvd.db.model.Card;
import com.solvd.db.model.Event;
import com.solvd.db.model.Transaction;
import com.solvd.db.model.User;
import com.solvd.enums.EnumEventName;
import com.solvd.services.AccountService;
import com.solvd.services.TransactionService;
import com.solvd.services.UserService;
import com.solvd.views.atm.admin.AtmTransactionView;

public abstract class AbstractTransactionController implements IAtmTransactionController {

    protected Account account;
    protected Card clientCard;
    protected Event event;
    protected Transaction transaction;
    protected User user;
    protected AccountService accountService;
    protected TransactionService transactionService;
    protected UserService userService;
    protected double amount = 0;
    protected EnumEventName eventType;
    protected final AtmTransactionView view = new AtmTransactionView();

    public AbstractTransactionController(Card clientCard) {
        this.clientCard = clientCard;
        this.event = new Event();
        this.transaction = new Transaction();
        this.accountService = new AccountService();
        this.transactionService = new TransactionService();
        this.userService = new UserService();
        this.user = userService.getUserByCardNumber(clientCard.getCardNumber());
        this.account = accountService.getAccountByUserId(user.getUserId());
    }

    @Override
    public void run() {
        try {
            updateBalance();
            eventType = getEventType();
            event = logEvent(clientCard, eventType);
            recordTransaction();
            promptPrintReceipt(view, transaction);
        } catch (Exception e) {
            view.display("An error occurred during the transaction process. Please try again.");
        }
    }

    @Override
    public abstract void updateBalance();

    @Override
    public abstract EnumEventName getEventType();

    // This method will be overridden in AtmDepositController where check balance is not necessary.
    @Override
    public void getTransactionAmount() {
        boolean validTransfer = false;

        while (!validTransfer) {
            amount = view.getTransactionAmount();

            double balance = checkBalance();
            if (amount <= balance) {
                run();
                validTransfer = true;
            } else {
                view.display("Insufficient balance.");
                if (handleInsufficientBalance() == 1) {
                    return;
                }
            }
        }
    }

    @Override
    public int handleInsufficientBalance() {
        view.displayInsufficientAmountChoices();
        return view.getUserChoice();
    }

    @Override
    public double checkBalance() {
        return account.getBalance();
    }

    @Override
    public void recordTransaction() {
        transaction.setStatus(EnumEventName.APPROVED.getEventName());
        transaction.setAmount(amount);
        transaction.setEvent(event);
        transactionService.insert(transaction);
        view.displayBody("Transaction completed!");
    }

}
