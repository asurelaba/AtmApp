package com.solvd.views;

import com.solvd.views.atm.AbstractAtmView;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    public String getUserInputString(String queryType) {
        display("Enter" + queryType);
        return s.next();
    }

    public long getUserInputLong(String queryType) {
        display("Enter" + queryType);
        return s.nextLong();
    }

    public List<Timestamp> getUserInputDate(String queryType) {
        List<Timestamp> timestamps = new ArrayList<>();
        display("Enter" + queryType + "from: ");
        timestamps.add(Timestamp.valueOf(s.nextLine()));
        display("Enter" + queryType + "to: ");
        timestamps.add(Timestamp.valueOf(s.nextLine()));
        return timestamps;
    }
}
