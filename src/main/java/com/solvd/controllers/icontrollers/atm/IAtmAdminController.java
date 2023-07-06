package com.solvd.controllers.icontrollers.atm;

import com.solvd.controllers.icontrollers.IFeatureController;

public interface IAtmAdminController extends IFeatureController {

    void handleCreateUser();

    void handleUnlockCardRequests();

    void handleLockUserCard();

    void handleLogout();

}
