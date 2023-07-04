package com.solvd.db.controllers;

import com.solvd.db.interfaces.IFeatureController;
import com.solvd.db.views.ExampleFeatureView;

public class ExampleFeatureController implements IFeatureController {

    private final long userCardNum;
    private final ExampleFeatureView view = new ExampleFeatureView();

    public ExampleFeatureController(long userCardNum) {
        this.userCardNum = userCardNum;
    }

    @Override
    public void run() {
        while (true) {
            view.displayExampleFeatureViewMenu();
            int menuChoice = view.getUserSelection();

            switch (menuChoice) {
                case 1 -> handleExampleFeature1();
                case 2 -> handleExampleFeature2();
                case 3 -> {
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

    private void handleExampleFeature2() {
        view.display("selected example feature 2");
        view.display("User Card Number: " + userCardNum);
    }

}
