package com.solvd.util;


import com.solvd.db.model.Transaction;
import com.solvd.services.AccountService;

import java.time.LocalDateTime;

public class ReceiptGenerator {

    String firstName;
    String lastName;
    String maskedCardNumber;
    String eventType;
    double amount;
    double balance;

    public void createReceipt(Transaction transaction) {

        firstName = transaction.getEvent().getCard().getUser().getPerson().getFirstName();
        lastName = transaction.getEvent().getCard().getUser().getPerson().getLastName();
        maskedCardNumber = "**** **** **** " + String.valueOf(transaction.getEvent().getCard().getCardNumber())
                .substring(String.valueOf(transaction.getEvent().getCard().getCardNumber()).length() - 4);
        eventType = transaction.getEvent().getEventType().getName();
        amount = transaction.getAmount();
        balance = new AccountService().getAccountByUserId(transaction.getEvent().getCard().getUser().getUserId())
                .getBalance();

        switch (eventType) {
            case "Withdrawal" -> printWithdrawalReceipt(transaction);
            case "Deposit" -> printDepositReceipt(transaction);
            case "Transfer" -> printTransferReceipt(transaction);
            case "Balance Inquiry" -> printBalanceInquiryReceipt(transaction);
            default -> System.out.println("This is not a Withdrawal, Deposit, " +
                    "Transfer, or Balance Inquiry transaction"); // TODO
        }
    }

    private void printWithdrawalReceipt(Transaction transaction) {
        System.out.println("DATE & TIME: " + LocalDateTime.now() + "\nUSER NAME: " + firstName + lastName +
                "\nCARD NUMBER " + maskedCardNumber + "\nEVENT TYPE: " + eventType + "\nAMOUNT: " + amount +
                "\nREMAIN BALANCE: " + balance);
    }

    private void printTransferReceipt(Transaction transaction) {
        System.out.println("DATE & TIME: " + LocalDateTime.now() + "\nUSER NAME: " + firstName + lastName +
                "\nCARD NUMBER " + maskedCardNumber + "\nEVENT TYPE: " + eventType + "\nAMOUNT: " + amount +
                "\nREMAIN BALANCE: " + balance);
    }

    private void printDepositReceipt(Transaction transaction) {
        System.out.println("DATE & TIME: " + LocalDateTime.now() + "\nUSER NAME: " + firstName + lastName +
                "\nCARD NUMBER " + maskedCardNumber + "\nEVENT TYPE: " + eventType + "\nAMOUNT: " + amount +
                "\nREMAIN BALANCE: " + balance);
    }

    private void printBalanceInquiryReceipt(Transaction transaction) {
        System.out.println("DATE & TIME: " + LocalDateTime.now() + "\nUSER NAME: " + firstName + lastName +
                "\nCARD NUMBER " + maskedCardNumber + "\nEVENT TYPE: " + eventType + "\nAMOUNT: " + amount +
                "\nREMAIN BALANCE: " + balance);
    }
}

