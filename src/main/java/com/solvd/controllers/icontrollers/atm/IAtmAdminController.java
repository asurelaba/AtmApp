package com.solvd.controllers.icontrollers.atm;

import com.solvd.controllers.icontrollers.IFeatureController;

public interface IAtmAdminController extends IFeatureController {

    void handleAddDeleteViewUsers();

    void handleUnlockCardRequests();

    void handleLockUserCard();

    void handleLogout();

}
