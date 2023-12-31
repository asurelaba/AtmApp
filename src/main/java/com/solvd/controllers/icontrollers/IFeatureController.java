package com.solvd.controllers.icontrollers;

import com.solvd.db.model.Card;
import com.solvd.db.model.Event;
import com.solvd.db.model.Transaction;
import com.solvd.enums.EnumEventName;
import com.solvd.services.EventService;
import com.solvd.services.EventTypeService;
import com.solvd.util.ReceiptGenerator;
import com.solvd.views.atm.AbstractAtmView;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.InputMismatchException;
import java.util.function.Supplier;
import java.util.regex.Pattern;

public interface IFeatureController {

    void run();

    default Event logEvent(Card card, EnumEventName eventName) {
        Event event = new Event();
        event.setCard(card);
        event.setDatetime(Timestamp.from(Instant.now()));
        event.setEventType(new EventTypeService().getEventTypeByTypeName(eventName.getEventName()));
        new EventService().insert(event);
        return event;
    }

    default void exitRun(AbstractAtmView view) {
        view.displayExit();
        while (true) {
            int userSel = view.getUserSelection();
            if (userSel == 0) {
                break;
            }
        }
    }

    default void promptPrintReceipt(AbstractAtmView view, Transaction transaction) {
        view.displayBody("Would you like a receipt for this transaction?");
        view.displayBody("1. Yes");
        view.displayBody("2. No");
        while (true) {
            int userSel = view.getUserSelection();
            if (userSel == 1) {
                view.displayBody("Printing Receipt..");
                ReceiptGenerator.createReceipt(transaction);
                break;
            } else if (userSel == 2) {
                break;
            }
        }
        view.displayBody("Returning to main menu..");
    }

    default Long cardNumberValidator(AbstractAtmView view, Supplier<String> getCardNumInput) {
        String cardNumber;
        while (true) {
            try {
                cardNumber = getCardNumInput.get();
                if (cardNumber.isEmpty()) {
                    throw new InputMismatchException(
                        "Card number cannot be empty");
                }
                if (cardNumber.length() != 16 || !Pattern.matches("\\d+", cardNumber)) {
                    throw new InputMismatchException("Card number must be comprised of 16 digits");
                }
                break;
            } catch (InputMismatchException e) {
                view.displayBody(e.getMessage(), "yellow");
            }
        }
        return Long.parseLong(cardNumber);
    }

}
