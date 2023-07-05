package com.solvd.views.atm;

import com.solvd.interfaces.iviews.atm.IAtmAdminView;
import java.util.Scanner;

public class AtmAdminView implements IAtmAdminView {

    private static final Scanner s = new Scanner(System.in);

    @Override
    public String featureTitle() {
        return "Admin Menu";
    }

    @Override
    public void display(String message) {
        System.out.println(message);
    }

    @Override
    public void displayAdminView() {
        display(System.lineSeparator());
        display(featureTitle());
        display("1. Create User");
        display("2. View Unlock Card Requests");
        display("3. Lock User Card");
        display("4. PlaceHolder - Example Feature");
        display("5. PlaceHolder - Example Feature");
        display("6. PlaceHolder - Example Feature");
        display("7. PlaceHolder - Example Feature");
        display("8. Logout");
        display("9. Shutdown AtmApp");
    }

    public int getUserSelection() {
        display("Enter selection choice: ");
        return s.nextInt();
    }

}
