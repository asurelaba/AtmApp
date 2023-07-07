package com.solvd.views.iviews.atm;

public interface IAtmView {

    int getUserSelection();

    String featureTitle();

    void display(String message);

    void displayTitle(String message);

    void displayBody(String message);

    void displayExit(String message);
}
