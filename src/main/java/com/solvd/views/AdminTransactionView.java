package com.solvd.views;

import com.solvd.views.atm.AbstractAtmView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AdminTransactionView extends AbstractAtmView {

    @Override
    public String featureTitle() {
        return "Transaction Query Menu";
    }

    public void displayAdminTransactionViewMenu() {
        display(System.lineSeparator());
        display(featureTitle());
        display("1. Query by Status");
        display("2. Query by Event Id");
        display("3. Query by Card Number");
        display("4. Query by Date");
        display("5. Query by User Id");
        display("6. Query by User Id and Date");
        display("7. return to main menu");
    }

    public String getUserInputString() {
        return s.next();
    }

    public long getUserInputLong() {
        return s.nextLong();
    }

    public Timestamp getUserInputDate() {
        return Timestamp.valueOf(s.next() + " 00:00:00");
    }
}
