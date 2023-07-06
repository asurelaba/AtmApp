package com.solvd.views.atm;

import com.solvd.views.iviews.atm.IAtmAdminView;

public class AtmAdminView extends AbstractAtmView implements IAtmAdminView {

    @Override
    public String featureTitle() {
        return "Admin Menu";
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

}