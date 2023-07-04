package com.solvd.interfaces.icontrollers.atm;

import com.solvd.interfaces.icontrollers.IFeatureController;

public interface IAtmAdminController extends IFeatureController {

    void handleCreateUser();

    void handleUnlockCardRequests();

    void handleLockUserCard();

    void handleLogout();

}
