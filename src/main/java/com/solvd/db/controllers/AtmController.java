package com.solvd.db.controllers;

import com.solvd.db.interfaces.IAtmController;
import com.solvd.db.views.AtmView;


public class AtmController implements IAtmController {

    // userCardNum and cardPin can be replaced by Card object
    protected long userCardNum;
    private int cardPin;
    private final String userType = "client";

    private static final AtmView view = new AtmView();

    @Override
    public void run() {
        userCardNum = view.getCardNumber();

        // TODO: cardController.validateCardNum()
        boolean isValidCardNum = userCardNum == 1;

        if (isValidCardNum) {
            cardPin = view.getCardPin();

            // TODO cardController.validateCardPin()
            boolean isValidCardPin = cardPin == 2;

            if (isValidCardPin) {
                handleMenuType();
            }
        } else {
            view.display("Invalid CardNumber.");
        }
    }

    private void handleMenuType() {
        switch (userType) {
            case "client":
                handleClientMenu();
                break;
            case "admin":
                displayAdminMenu();
                break;
        }
    }

    public void handleClientMenu() {
        while (true) {
            view.displayClientMenu();
            int clientInput = view.getUserSelection();

            switch (clientInput) {
                case 1 -> handleExampleFeature();
                case 2 -> handleRequestCardLock();
                case 3 -> atmReset();
                case 4 -> System.exit(0);
                default -> {
                    view.display("Invalid selection");
                    handleRequestCardLock();
                }
            }
        }
    }

    private void handleExampleFeature() {
        view.display("handle example feature1");
        new ExampleFeatureController(userCardNum).run();
    }

    private void handleRequestCardLock() {
        view.displayRequestCardLock();
        int cardLockMenuChoice = view.getUserSelection();
        switch (cardLockMenuChoice) {
            case 1:
                // Do card lock operation
                view.display("Card Locked.");
            case 2:
                view.display("Returning to menu.");
                break;
            default:
                view.display("Invalid selection");
                handleRequestCardLock();
        }
    }

    @Override
    public void atmReset() {
        view.display("logging out");
        userCardNum = 0;
        cardPin = 0;
        run();
    }

    private void displayAdminMenu() {
    }

}
