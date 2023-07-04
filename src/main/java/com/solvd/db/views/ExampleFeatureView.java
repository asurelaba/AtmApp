package com.solvd.db.views;

import com.solvd.db.interfaces.IFeatureView;
import java.util.Scanner;

public class ExampleFeatureView implements IFeatureView {

    private static final Scanner s = new Scanner(System.in);

    @Override
    public String featureTitle() {
        return "Example Feature";
    }

    public void display(String message) {
        System.out.println(message);
    }

    public void displayExampleFeatureViewMenu() {
        display(System.lineSeparator());
        display(featureTitle());
        display("1. ExampleFeatureSel1");
        display("2. Display user card number");
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
