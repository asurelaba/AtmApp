package com.solvd.controllers;

import com.solvd.controllers.icontrollers.IFeatureController;

// TODO Delete file after refactoring common methods to IFeatureController confirmation
public abstract class AbstractFeatureController implements IFeatureController {

    @Override
    public abstract void run();

}

