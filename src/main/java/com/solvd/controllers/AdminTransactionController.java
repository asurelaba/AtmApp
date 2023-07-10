package com.solvd.controllers;

import com.solvd.controllers.icontrollers.IFeatureController;
import com.solvd.db.model.Transaction;
import com.solvd.views.AdminTransactionView;

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

    private void handleExampleFeature1() {
        view.displayExampleFeature();
    }

    private void handleShowUser() {
        view.display("User: " + userCard.getUser());
    }

    private void handleTransactionsByStatus() {
    }

    private void handleTransactionByEventId() {
    }

    private void handleTransactionsByCardNumber() {
    }

    private void handleTransactionsByDateRange() {
    }

    private void handleTransactionsByUserId() {
    }

    private void handleTransactionsByUserIdAndDateRange() {
    }
}
