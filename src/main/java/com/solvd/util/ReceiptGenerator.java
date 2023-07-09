package com.solvd.util;


import com.solvd.EnumEventNames;
import com.solvd.db.model.Transaction;
import com.solvd.services.AccountService;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class ReceiptGenerator {

    public static void createReceipt(Transaction transaction) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm:ss a");
        DecimalFormat decimalFormat = new DecimalFormat("#.00");

        String firstName = transaction.getEvent().getCard().getUser().getPerson().getFirstName();
        String lastName = transaction.getEvent().getCard().getUser().getPerson().getLastName();
        String maskedCardNumber = "**** **** **** " + String.valueOf(transaction.getEvent().getCard().getCardNumber())
                .substring(String.valueOf(transaction.getEvent().getCard().getCardNumber()).length() - 4);
        String eventType = transaction.getEvent().getEventType().getName();
        double balance = new AccountService().getAccountByUserId(transaction.getEvent().getCard().getUser().getUserId())
                .getBalance();

        if (eventType.contentEquals(EnumEventNames.BALANCE_INQUIRY.getEventName())) {
            System.out.println("\n**********************************************************************************\n" +
                    "DATE & TIME: " + LocalDateTime.now().format(formatter) + "\nUSER NAME: " + firstName + " " + lastName +
                    "\nCARD NUMBER " + maskedCardNumber + "\nEVENT TYPE: " + eventType + "\nREMAIN BALANCE: " +
                    decimalFormat.format(balance) +
                    "\n**********************************************************************************\n");
        } else {
            double amount = transaction.getAmount();
            System.out.println("\n**********************************************************************************\n" +
                    "DATE & TIME: " + LocalDateTime.now().format(formatter) + "\nUSER NAME: " + firstName + " " + lastName +
                    "\nCARD NUMBER " + maskedCardNumber + "\nEVENT TYPE: " + eventType + "\nAMOUNT: " +
                    decimalFormat.format(amount) + "\nREMAIN BALANCE: " + decimalFormat.format(balance) +
                    "\n**********************************************************************************\n");
        }
    }
}