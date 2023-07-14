package com.solvd.views.atm.admin;

import com.solvd.views.atm.AbstractAtmView;
import com.solvd.views.iviews.atm.IAtmAdminView;

public class AtmAdminView extends AbstractAtmView implements IAtmAdminView {

    @Override
    public String featureTitle() {
        return "Admin Menu";
    }

    @Override
    public void displayAdminView() {
        display(featureTitle());
        display("1. Add/delete/View Users");
        display("2. Transactions Query Menu");
        display("3. Manager Client Accounts");
        display("4. Modify Cards");
        display("5. Change Pin");
        display("6. Shutdown AtmApp");
        display("0. Logout");
    }

}
