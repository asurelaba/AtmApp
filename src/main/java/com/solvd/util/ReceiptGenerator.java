package com.solvd.util;


import com.solvd.EnumEventNames;
import com.solvd.db.model.Transaction;
import com.solvd.services.AccountService;

import java.time.LocalDateTime;

public abstract class ReceiptGenerator {

    public static void createReceipt(Transaction transaction) {

        String firstName = transaction.getEvent().getCard().getUser().getPerson().getFirstName();
        String lastName = transaction.getEvent().getCard().getUser().getPerson().getLastName();
        String maskedCardNumber = "**** **** **** " + String.valueOf(transaction.getEvent().getCard().getCardNumber())
                .substring(String.valueOf(transaction.getEvent().getCard().getCardNumber()).length() - 4);
        String eventType = transaction.getEvent().getEventType().getName();
        double balance = new AccountService().getAccountByUserId(transaction.getEvent().getCard().getUser().getUserId())
                .getBalance();

        if (eventType.contentEquals(EnumEventNames.BALANCE_INQUIRY.getEventName())) {
            System.out.println("\n**********************************************************************************\n" +
                    "DATE & TIME: " + LocalDateTime.now() + "\nUSER NAME: " + firstName + " " + lastName +
                    "\nCARD NUMBER " + maskedCardNumber + "\nEVENT TYPE: " + eventType + "\nREMAIN BALANCE: " +
                    balance +
                    "\n**********************************************************************************\n");
        } else {
            double amount = transaction.getAmount();
            System.out.println("\n**********************************************************************************\n" +
                    "DATE & TIME: " + LocalDateTime.now() + "\nUSER NAME: " + firstName + " " + lastName +
                    "\nCARD NUMBER " + maskedCardNumber + "\nEVENT TYPE: " + eventType + "\nAMOUNT: " + amount +
                    "\nREMAIN BALANCE: " + balance +
                    "\n**********************************************************************************\n");
        }
    }
}