package com.solvd.controllers.atm;

import com.solvd.controllers.icontrollers.IFeatureController;
import com.solvd.db.model.Card;
import com.solvd.db.model.Transaction;
import com.solvd.enums.EnumEventNames;
import com.solvd.services.AccountService;
import com.solvd.services.TransactionService;
import com.solvd.views.atm.AdminTransactionView;

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
        logEvent(adminCard, EnumEventNames.TRANSACTION_QUERY);

        String queryType = "Status";

        // Get query input from user
        view.displayBody("Enter " + queryType + ":");
        String input = view.getUserInputString();

        // Display query
        if (input != null) {
            List<Transaction> transactions = new TransactionService().getTransactionsByStatus(input);
            if (transactions != null && !transactions.isEmpty()) {
                view.displayBody("Transactions by " + queryType + ":");
                formatObject(transactions);
            } else {
                view.displayBody("No transactions found for the given " + queryType);
            }
        }

        exitRun(view);
    }

    // Event Id
    private void handleTransactionByEventId() {
        logEvent(adminCard, EnumEventNames.TRANSACTION_QUERY);

        String queryType = "Event Id";

        // Get query input from user
        view.displayBody("Enter " + queryType + ":");
        int input = view.getUserInputInt();

        //Display query
        if (input != 0) {
            Transaction transaction = new TransactionService().getTransactionByEventId(input);
            if (transaction != null) {
                view.displayBody("Transactions by " + queryType + ":\n");
                formatObject(transaction);
            } else {
                view.displayBody("No transactions found for the given " + queryType);
            }
        }

        exitRun(view);
    }

    private void handleTransactionsByCardNumber() {
        logEvent(adminCard, EnumEventNames.TRANSACTION_QUERY);

        String queryType = "Card Number";

        // Get query input from user
        view.displayBody("Enter " + queryType + ":");
        long input = view.getUserInputLong();

        // Display query
        if (input != 0) {
            List<Transaction> transactions = new TransactionService().getTransactionsByCardNumber(input);
            if (transactions != null && !transactions.isEmpty()) {
                view.displayBody("Transactions by " + queryType + ":");
                formatObject(transactions);
            } else {
                view.displayBody("No transactions found for the given " + queryType);
            }
        }

        exitRun(view);
    }

    private void handleTransactionsByDateRange() {
        logEvent(adminCard, EnumEventNames.TRANSACTION_QUERY);

        String queryType = "Date-Range";

        // Get query input from user
        view.displayBody("Enter " + queryType + " from:");
        Timestamp from = view.getUserInputDate();
        view.displayBody("Enter " + queryType + " to:");
        Timestamp to = view.getUserInputDate();

        // Display query
        if (from != null && to != null) {
            List<Transaction> transactions = new TransactionService().getTransactionsByDateRange(from, to);
            if (transactions != null && !transactions.isEmpty()) {
                view.displayBody("Transactions by " + queryType + ":");
                formatObject(transactions);
            } else {
                view.displayBody("No transactions found for the given " + queryType);
            }
        }

        exitRun(view);
    }

    private void handleTransactionsByUserId() {
        logEvent(adminCard, EnumEventNames.TRANSACTION_QUERY);

        String queryType = "User Id";

        // Get query input from user
        view.displayBody("Enter " + queryType + ":");
        int input = view.getUserInputInt();

        // Display query
        if (input != 0) {
            List<Transaction> transactions = new TransactionService().getTransactionsByUserId(input);
            if (transactions != null && !transactions.isEmpty()) {
                view.displayBody("Transactions by " + queryType + ":");
                formatObject(transactions);
            } else {
                view.displayBody("No transactions found for the given " + queryType);
            }
        }

        exitRun(view);
    }

    private void handleTransactionsByUserIdAndDateRange() {
        logEvent(adminCard, EnumEventNames.TRANSACTION_QUERY);

        String queryType = "Date Range and User Id";

        // Get query input from user
        view.displayBody("Enter " + queryType + " from:");
        Timestamp from = view.getUserInputDate();
        view.displayBody("Enter " + queryType + " to:");
        Timestamp to = view.getUserInputDate();
        view.displayBody("Enter " + queryType + " User Id:");
        int userId = view.getUserInputInt();

        // Display query
        if (from != null && to != null && userId != 0) {
            List<Transaction> transactions = new TransactionService().
                    getTransactionsByUserIdAndDateRange(userId, from, to);
            if (transactions != null && !transactions.isEmpty()) {
                view.displayBody("Transactions by " + queryType + ":");
                formatObject(transactions);
            } else {
                view.displayBody("No transactions found for the given " + queryType);
            }
        }

        exitRun(view);
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

        // width of cells
        int width = 11;
        int width2 = 19;

        // Header
        view.displayBody(view.centerAndTrim("Date", width2) + " | " + view.centerAndTrim("Transaction", width2) +
                " | " + view.centerAndTrim("Status", width) + " | " + view.centerAndTrim("Amount", width) +
                " | " + view.centerAndTrim("Balance", width) + " | " + view.centerAndTrim("Transaction id", width)
                + " | " + view.centerAndTrim("Event Id", width) + " | " + view.centerAndTrim("Account Id", width)
                + " | " + view.centerAndTrim("Card Number", width) + " | " + view.centerAndTrim("User Id", width)
                + " | " + view.centerAndTrim("First Name", width) + " | " + view.centerAndTrim("Last Name", width)
        );

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

            // Data
            view.displayBody(view.centerAndTrim(date, width2) + " | " + view.centerAndTrim(transaction, width2) +
                    " | " + view.centerAndTrim(status, width) + " | " + view.centerAndTrim(amount, width) +
                    " | " + view.centerAndTrim(balance, width) + " | " + view.centerAndTrim(transactionId, width) +
                    " | " + view.centerAndTrim(eventId, width) + " | " + view.centerAndTrim(accountId, width) +
                    " | " + view.centerAndTrim(userId, width) + " | " + view.centerAndTrim(cardNumber, width) +
                    " | " + view.centerAndTrim(firstName, width) + " | " + view.centerAndTrim(lastName, width));
        }
    }

    private void formatObject(Transaction t) {
        // width of cells
        int width = 11;
        int width2 = 19;

        // Pull data
        String date = t.getEvent().getDatetime().toString();
        String transactionName = t.getEvent().getEventType().getEventTypeName();
        String status = t.getStatus();
        String amount = String.valueOf(t.getAmount());
        String balance = String.valueOf(new AccountService().getAccountByUserId(t.getEvent().getCard().getUser().
                getUserId()).getBalance());
        String transactionId = String.valueOf(t.getTransactionId());
        String eventId = String.valueOf(t.getEvent().getEventId());
        String accountId = String.valueOf(new AccountService().getAccountByUserId(t.getEvent().getCard().getUser()
                .getUserId()).getAccountId());
        String userId = String.valueOf(t.getEvent().getCard().getUser().getUserId());
        String cardNumber = String.valueOf(t.getEvent().getCard().getCardNumber());
        String firstName = t.getEvent().getCard().getUser().getPerson().getFirstName();
        String lastName = t.getEvent().getCard().getUser().getPerson().getLastName();

        // Header
        view.displayBody(view.centerAndTrim("Date", width2) + " | " + view.centerAndTrim("Transaction", width2) +
                " | " + view.centerAndTrim("Status", width) + " | " + view.centerAndTrim("Amount", width) +
                " | " + view.centerAndTrim("Balance", width) + " | " + view.centerAndTrim("Transaction id", width)
                + " | " + view.centerAndTrim("Event Id", width) + " | " + view.centerAndTrim("Account Id", width)
                + " | " + view.centerAndTrim("Card Number", width) + " | " + view.centerAndTrim("User Id", width)
                + " | " + view.centerAndTrim("First Name", width) + " | " + view.centerAndTrim("Last Name", width)
        );

        // Data
        view.displayBody(view.centerAndTrim(date, width2) + " | " + view.centerAndTrim(transactionName, width2) +
                " | " + view.centerAndTrim(status, width) + " | " + view.centerAndTrim(amount, width) +
                " | " + view.centerAndTrim(balance, width) + " | " + view.centerAndTrim(transactionId, width) +
                " | " + view.centerAndTrim(eventId, width) + " | " + view.centerAndTrim(accountId, width) +
                " | " + view.centerAndTrim(userId, width) + " | " + view.centerAndTrim(cardNumber, width) +
                " | " + view.centerAndTrim(firstName, width) + " | " + view.centerAndTrim(lastName, width));
    }
}