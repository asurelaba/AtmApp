package com.solvd.controllers;

import com.solvd.db.model.Card;
import com.solvd.controllers.icontrollers.IFeatureController;
import com.solvd.views.ExampleFeatureView;

public class ExampleFeatureController implements IFeatureController {

    private final Card userCard;
    private final ExampleFeatureView view = new ExampleFeatureView();

    public ExampleFeatureController(Card userCard) {
        this.userCard = userCard;
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
        view.display("User: " + userCard.getUser());
    }

}
