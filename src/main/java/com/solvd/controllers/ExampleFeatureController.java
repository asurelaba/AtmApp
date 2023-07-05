package com.solvd.controllers;

import com.solvd.db.model.User;
import com.solvd.interfaces.icontrollers.IFeatureController;
import com.solvd.views.ExampleFeatureView;

public class ExampleFeatureController implements IFeatureController {

    private final User user;
    private final ExampleFeatureView view = new ExampleFeatureView();

    public ExampleFeatureController(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        while (true) {
            view.displayExampleFeatureViewMenu();
            int menuChoice = view.getUserSelection();

            switch (menuChoice) {
                case 1 -> handleExampleFeature1();
                case 2 -> handleShowUser();
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

    private void handleShowUser() {
        view.display("User: " + user);
    }

}
