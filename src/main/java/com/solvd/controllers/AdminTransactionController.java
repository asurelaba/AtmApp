package com.solvd.controllers;

import com.solvd.controllers.icontrollers.IFeatureController;
import com.solvd.db.model.Transaction;
import com.solvd.services.TransactionService;
import com.solvd.views.AdminTransactionView;

import java.sql.Timestamp;
import java.util.stream.Collectors;

public class AdminTransactionController implements IFeatureController {
    private final Transaction transaction;
    private final AdminTransactionView view = new AdminTransactionView();

    public AdminTransactionController(Transaction transaction) {
        this.transaction = transaction;
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

    private void handleTransactionsByStatus() {
        String queryType = "Status";
        view.display("Enter" + queryType + ":");
        String status = view.getUserInputString(queryType);
        view.display("Transactions by" + queryType + ":\n" + new TransactionService()
                .getTransactionsByStatus(status)
                .stream() // parse into lines for friendly display
                .map(Transaction::toString)
                .collect(Collectors.joining("\n")));
    }

    private void handleTransactionByEventId() {
        String queryType = "Event Id";

        // Get query input from user
        view.display("Enter" + queryType + ":");
        long input = view.getUserInputLong(queryType);

        //Display query
        view.display("Transactions by" + queryType + ":\n" + new TransactionService()
                .getTransactionsByCardNumber(input)
                .stream() // parse into lines for friendly display
                .map(Transaction::toString)
                .collect(Collectors.joining("\n")));
    }

    private void handleTransactionsByCardNumber() {
        String queryType = "Card Number";

        // Get query input from user
        view.display("Enter" + queryType + ":");
        long input = view.getUserInputLong(queryType);

        //Display query
        view.display("Transactions by" + queryType + ":\n" + new TransactionService()
                .getTransactionsByCardNumber(input)
                .stream() // parse into lines for friendly display
                .map(Transaction::toString)
                .collect(Collectors.joining("\n")));
    }

    private void handleTransactionsByDateRange() {
        String queryType = "Date Range";

        // Get query input from user
        view.display("Enter" + queryType + ":");
        Timestamp from = view.getUserInputDate(queryType).get(0);
        Timestamp to = view.getUserInputDate("Date Range").get(0);

        //Display query
        view.display("Transactions by" + queryType + ":\n" + new TransactionService()
                .getTransactionsByDateRange(from, to)
                .stream() // parse into lines for friendly display
                .map(Transaction::toString)
                .collect(Collectors.joining("\n")));
    }

    private void handleTransactionsByUserId() {
        String queryType = "User Id";

        // Get query input from user
        view.display("Enter" + queryType + ":");
        long input = view.getUserInputLong(queryType);

        //Display query
        view.display("Transactions by" + queryType + ":\n" + new TransactionService()
                .getTransactionsByCardNumber(input)
                .stream() // parse into lines for friendly display
                .map(Transaction::toString)
                .collect(Collectors.joining("\n")));
    }

    private void handleTransactionsByUserIdAndDateRange() {
        String queryType = "Date Range and User Id";

        // Get query input from user
        view.display("Enter" + queryType + ":");
        Timestamp from = view.getUserInputDateAndUserId(queryType).get(0);
        Timestamp to = view.getUserInputDateAndUserId("Date Range").get(0);
        int userId = (int) view.getUserInputLong(queryType);

        //Display query
        view.display("Transactions by" + queryType + ":\n" + new TransactionService()
                .getTransactionsByUserIdAndDateRange(userId, from, to)
                .stream() // parse into lines for friendly display
                .map(Transaction::toString)
                .collect(Collectors.joining("\n")));
    }
}
