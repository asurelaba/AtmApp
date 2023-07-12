package com.solvd.controllers.icontrollers.atm;

public interface IAtmTransferController extends IAtmTransactionController {

    void getRecipientAccountId();

    void validateRecipientAccount();
}
