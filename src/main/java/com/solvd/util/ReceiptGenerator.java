package com.solvd.util;

import com.solvd.db.model.Transaction;
import com.solvd.enums.EnumEventName;
import com.solvd.services.AccountService;
import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReceiptGenerator {

    public static void createReceipt(Transaction transaction) {
        final Logger LOGGER = LogManager.getLogger(ReceiptGenerator.class);

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy - hh:mm:ss a");
            DecimalFormat decimalFormat = new DecimalFormat("#.00");

            String firstName = transaction.getEvent().getCard().getUser().getPerson()
                .getFirstName();
            String lastName = transaction.getEvent().getCard().getUser().getPerson().getLastName();
            String maskedCardNumber =
                "**** **** **** " + String.valueOf(transaction.getEvent().getCard().getCardNumber())
                    .substring(
                        String.valueOf(transaction.getEvent().getCard().getCardNumber()).length()
                            - 4);
            String eventType = transaction.getEvent().getEventType().getEventTypeName();
            double balance = new AccountService().getAccountByUserId(
                    transaction.getEvent().getCard().getUser().getUserId())
                .getBalance();
            int account = new AccountService()
                .getAccountByUserId(transaction.getEvent().getCard().getUser().getUserId())
                .getAccountId();

            StringBuilder receipt = new StringBuilder();
            receipt.append(
                "********************************** ATM RECEIPT ***********************************\n");
            receipt.append("DATE & TIME: ").append(LocalDateTime.now().format(formatter))
                .append("\n");
            receipt.append("ACCOUNT: ").append(account).append("\n");
            receipt.append("USER NAME: ").append(firstName).append(" ").append(lastName)
                .append("\n");
            receipt.append("CARD NUMBER: ").append(maskedCardNumber).append("\n");
            receipt.append("EVENT TYPE: ").append(eventType).append("\n");

            if (eventType.contentEquals(EnumEventName.BALANCE_INQUIRY.getEventName())) {
                receipt.append("REMAIN BALANCE: ").append(decimalFormat.format(balance))
                    .append("\n");
            } else {
                double amount = transaction.getAmount();
                receipt.append("AMOUNT: ").append(decimalFormat.format(amount)).append("\n");
                receipt.append("REMAIN BALANCE: ").append(decimalFormat.format(balance))
                    .append("\n");
            }

            receipt.append(
                "**********************************************************************************\n");

            Printer.print(receipt.toString());

        } catch (PrinterException e) {
            LOGGER.error("Error occurred while printing the receipt");
        }
    }

}