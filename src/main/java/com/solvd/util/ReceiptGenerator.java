package com.solvd.util;

import com.solvd.EnumEventNames;
import com.solvd.db.model.Transaction;
import com.solvd.services.AccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class ReceiptGenerator {

    public static void createReceipt(Transaction transaction) {
        final Logger LOGGER = LogManager.getLogger(ReceiptGenerator.class);

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - hh:mm:ss a");
            DecimalFormat decimalFormat = new DecimalFormat("#.00");

            String firstName = transaction.getEvent().getCard().getUser().getPerson().getFirstName();
            String lastName = transaction.getEvent().getCard().getUser().getPerson().getLastName();
            String maskedCardNumber = "**** **** **** " + String.valueOf(transaction.getEvent().getCard().getCardNumber())
                    .substring(String.valueOf(transaction.getEvent().getCard().getCardNumber()).length() - 4);
            String eventType = transaction.getEvent().getEventType().getName();
            double balance = new AccountService().getAccountByUserId(transaction.getEvent().getCard().getUser().getUserId())
                    .getBalance();

            StringBuilder receipt = new StringBuilder();
            receipt.append("\n**********************************************************************************\n");
            receipt.append("DATE & TIME: ").append(LocalDateTime.now().format(formatter)).append("\n");
            receipt.append("USER NAME: ").append(firstName).append(" ").append(lastName).append("\n");
            receipt.append("CARD NUMBER: ").append(maskedCardNumber).append("\n");
            receipt.append("EVENT TYPE: ").append(eventType).append("\n");

            if (eventType.contentEquals(EnumEventNames.BALANCE_INQUIRY.getEventName())) {
                receipt.append("REMAIN BALANCE: ").append(decimalFormat.format(balance)).append("\n");
            } else {
                double amount = transaction.getAmount();
                receipt.append("AMOUNT: ").append(decimalFormat.format(amount)).append("\n");
                receipt.append("REMAIN BALANCE: ").append(decimalFormat.format(balance)).append("\n");
            }

            receipt.append("**********************************************************************************\n");

            Printer.print(receipt.toString());

            // Log the event
            LOGGER.info("Receipt created for transaction (eventID): {}", transaction.getEvent().getEventId());

        } catch (PrinterException e) {
            LOGGER.error("Error occurred while printing the receipt:", e);
        }
    }
}