package com.solvd.controllers.atm;

import com.solvd.controllers.icontrollers.atm.IAtmTransactionController;
import com.solvd.db.model.*;
import com.solvd.services.*;
import com.solvd.views.atm.AtmTransactionView;

import java.sql.Timestamp;

public abstract class AbstractTransactionController implements IAtmTransactionController {

    protected Account account;
    protected Card clientCard;
    protected Event event;
    protected Transaction transaction;
    protected User user;
    protected AccountService accountService;
    protected EventService eventService;
    protected EventTypeService eventTypeService;
    protected TransactionService transactionService;
    protected UserService userService;
    protected double amount = 0;
    protected String eventType;
    protected final AtmTransactionView view = new AtmTransactionView();

    public AbstractTransactionController(Card clientCard) {
        this.clientCard = clientCard;
        this.event = new Event();
        this.transaction = new Transaction();
        this.accountService = new AccountService();
        this.eventService = new EventService();
        this.eventTypeService = new EventTypeService();
        this.transactionService = new TransactionService();
        this.userService = new UserService();
        this.user = userService.getUserByCardNumber(clientCard.getCardNumber());
        this.account = accountService.getAccountByUserId(user.getUserId());
    }

    @Override
    public void run() {
        try {
            updateBalance();
            setEventType();
            recordEvent(eventType);
            recordTransaction();
            printReceiptOrNot();
            exit();
        } catch (Exception e) {
            view.display("An error occurred during the transaction process. Please try again.");
        }
    }

    @Override
    public abstract void updateBalance();

    @Override
    public abstract void setEventType();

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
                    new AtmClientController(clientCard);
                    validTransfer = true;
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
    public void recordEvent(String eventType) {
        event.setCard(clientCard);
        event.setEventType(eventTypeService.getEventTypeByTypeName(eventType));
        event.setDatetime(Timestamp.valueOf(java.time.LocalDateTime.now()));
        eventService.insert(event);
    }

    @Override
    public void recordTransaction() {
        transaction.setStatus("approved");
        transaction.setAmount(amount);
        transaction.setEvent(event);
        transactionService.insert(transaction);
    }

    @Override
    public void printReceiptOrNot() {
        view.displayPrintReceiptChoices();
        int userSel = view.getUserChoice();

        if (userSel == 1) {
            displayReceipt();
        }
    }

    @Override
    public void displayReceipt() {
        long cardNumberLong = clientCard.getCardNumber();
        String cardNumberString = Long.toString(cardNumberLong);
        String lastFourDigits = cardNumberString.substring(cardNumberString.length() - 4);
        String receipt = "\n\n----------------- ATM Receipt -----------------\n" +
                "DATE & TIME: " + java.time.LocalDateTime.now() + "\n" +
                "USER NAME: " + user.getPerson().getFirstName() + " " + user.getPerson().getLastName() + "\n" +
                "CARD NUMBER (LAST 4 DIGITS): **** **** **** " + lastFourDigits + "\n" +
                "EVENT TYPE: " + eventType + "\n" +
                getAdditionalInfo() +
                "AMOUNT: $" + amount + "\n" +
                "REMAINING BALANCE: $" + checkBalance() + "\n" +
                "-----------------------------------------------\n";

        view.display(receipt);
    }

    // This method will be overridden in AtmTransferController where recipient account will be provided.
    @Override
    public String getAdditionalInfo() {
        return "";
    }

    @Override
    public void exit() {
        view.displayExitChoices();
        int userSel = view.getUserChoice();

        if (userSel == 2) {
            handleLogout();
        }
    }

    @Override
    public void handleLogout() {
        view.displayLogoutMessage(user.getPerson().getFirstName());
        new AtmController().run();
    }
}
