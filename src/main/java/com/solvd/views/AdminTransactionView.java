package com.solvd.views;

import com.solvd.views.atm.AbstractAtmView;

public class AdminTransactionView extends AbstractAtmView {

    @Override
    public String featureTitle() {
        return "Transaction Administration";
    }

    public void displayAdminTransactionViewMenu() {
        display(System.lineSeparator());
        display(featureTitle());
        display("1. Transaction query by Status");
        display("2. Transaction query by Event Id");
        display("3. Transaction query by Card Number");
        display("4. Transaction query by Date");
        display("5. Transaction query by User Id");
        display("6. Transaction query by User Id and Date");
        display("7. return to main menu");
    }

    public void displayQueryByStatus(){
        display(System.lineSeparator());
        display(featureTitle());
        display("1. Transaction query by Status");

    }
    public void displayQueryByEventId() {}
    public void displayQueryByCardNumber(){}
    public void displayQueryByDate(){}
    public void displayQueryByUserId() {}
    public void displayQueryByUserIdAndDate() {}

    public String getUserInput(String typeOfAction) {
        display("Enter" + typeOfAction );
        return s.next();
    }

    public void displayExampleFeature() {
        display("selected feature 1");
    }
}
