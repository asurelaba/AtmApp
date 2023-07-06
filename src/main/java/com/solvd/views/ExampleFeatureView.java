package com.solvd.views;

import com.solvd.views.iviews.atm.IAtmView;
import com.solvd.views.atm.AbstractAtmView;

public class ExampleFeatureView extends AbstractAtmView implements IAtmView {

    @Override
    public String featureTitle() {
        return "Example Feature";
    }

    public void displayExampleFeatureViewMenu() {
        display(System.lineSeparator());
        display(featureTitle());
        display("1. Example Feature 1");
        display("2. Display user info");
        display("3. return to main menu");
    }

    public int getUserSelection() {
        display("Enter selection choice: ");
        return s.nextInt();
    }

    public void displayExampleFeature() {
        display("selected feature 1");
    }

}
