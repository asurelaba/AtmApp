package com.solvd.controllers;

import com.solvd.controllers.icontrollers.IFeatureController;
import com.solvd.db.model.Card;
import com.solvd.db.model.Transaction;
import com.solvd.services.AccountService;
import com.solvd.services.TransactionService;
import com.solvd.views.AdminTransactionView;

import java.sql.Timestamp;
import java.util.List;

public class AdminTransactionController implements IFeatureController {

    private final Card adminCard;
    private final AdminTransactionView view = new AdminTransactionView();

    public AdminTransactionController(Card adminCard) {
        this.adminCard = adminCard;
    }

    @Override
    public void run() {
        while (true) {
            view.displayAdminTransactionViewMenu();
            int menuChoice = view.getUserSelection();

            switch (menuChoice) {
                case 1 -> handleTransactionsByStatus();
                case 2 -> handleTransactionByEventId();
                case 3 -> handleTransactionsByCardNumber();
                case 4 -> handleTransactionsByDateRange();
                case 5 -> handleTransactionsByUserId();
                case 6 -> handleTransactionsByUserIdAndDateRange();
                case 7 -> {
                    view.display("Returning to the main menu...");
                    return;
                }
                default -> view.display("Invalid choice. Please try again.");
            }
        }
    }

    // Status
    private void handleTransactionsByStatus() {
        String queryType = "Status";

        // Get query input from user
        view.display("Enter " + queryType + ":");
        String input = view.getUserInputString();

        //Display query
        view.display("Transactions by " + queryType + ":");
        formatObject(new TransactionService().getTransactionsByStatus(input));
    }

    // Event Id
    private void handleTransactionByEventId() {
        String queryType = "Event Id";

        // Get query input from user
        view.display("Enter " + queryType + ":");
        int input = view.getUserInputInt();

        //Display query
        view.display("Transactions by " + queryType + ":\n" + new TransactionService()
                .getTransactionByEventId(input));
    }

    private void handleTransactionsByCardNumber() {
        String queryType = "Card Number";

        // Get query input from user
        view.display("Enter " + queryType + ":");
        long input = view.getUserInputLong();

        //Display query
        view.display("Transactions by " + queryType + ":");
        formatObject(new TransactionService().getTransactionsByCardNumber(input));
    }

    private void handleTransactionsByDateRange() {
        String queryType = "Date-Range";

        // Get query input from user
        view.display("Enter " + queryType + " from:");
        Timestamp from = view.getUserInputDate();
        view.display("Enter " + queryType + " to:");
        Timestamp to = view.getUserInputDate();

        //Display query
        view.display("Transactions by " + queryType + ":");
        formatObject(new TransactionService().getTransactionsByDateRange(from, to));
    }

    private void handleTransactionsByUserId() {
        String queryType = "User Id";

        // Get query input from user
        view.display("Enter " + queryType + ":");
        int input = view.getUserInputInt();

        //Display query
        view.display("Transactions by " + queryType + ":");
        formatObject(new TransactionService().getTransactionsByUserId(input));
    }

    private void handleTransactionsByUserIdAndDateRange() {
        String queryType = "Date Range and User Id";

        // Get query input from user
        view.display("Enter " + queryType + " from:");
        Timestamp from = view.getUserInputDate();
        view.display("Enter " + queryType + " to:");
        Timestamp to = view.getUserInputDate();
        view.display("Enter " + queryType + " User Id:");
        int userId = view.getUserInputInt();

        //Display query
        view.display("Transactions by " + queryType + ":");
        formatObject(new TransactionService().getTransactionsByUserIdAndDateRange(userId, from, to));
    }

    // Format object for user view
    private void formatObject(List<Transaction> transactions) {
        String date;
        String transaction;
        String status;
        String amount;
        String balance;
        String transactionId;
        String eventId;
        String accountId;
        String cardNumber;
        String userId;
        String firstName;
        String lastName;
        view.display("Date | Transaction | Status | Amount | Balance | Transaction id | Event Id | Account Id | " +
                "Card Number | User Id | First Name | Last Name");
        for (Transaction t : transactions) {
            date = t.getEvent().getDatetime().toString();
            transaction = t.getEvent().getEventType().getEventTypeName();
            status = t.getStatus();
            amount = String.valueOf(t.getAmount());
            balance = String.valueOf(new AccountService().getAccountByUserId(t.getEvent().getCard().getUser().
                    getUserId()).getBalance());
            transactionId = String.valueOf(t.getTransactionId());
            eventId = String.valueOf(t.getEvent().getEventId());
            accountId = String.valueOf(new AccountService().getAccountByUserId(t.getEvent().getCard().getUser()
                    .getUserId()).getAccountId());
            userId = String.valueOf(t.getEvent().getCard().getUser().getUserId());
            cardNumber = String.valueOf(t.getEvent().getCard().getCardNumber());
            firstName = t.getEvent().getCard().getUser().getPerson().getFirstName();
            lastName = t.getEvent().getCard().getUser().getPerson().getLastName();

            view.display(date + " | " + transaction + " | " + status + " | " + amount + " | " + balance + " | " +
                    transactionId + " | " + eventId + " | " + accountId + " | " + userId + " | " + cardNumber + " | " +
                    firstName + " | " + lastName);
        }
    }
}
