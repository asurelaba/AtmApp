package com.solvd.controllers.icontrollers;

import com.solvd.EnumEventNames;
import com.solvd.db.model.Card;
import com.solvd.db.model.Event;
import com.solvd.db.model.Transaction;
import com.solvd.services.EventService;
import com.solvd.services.EventTypeService;
import com.solvd.util.ReceiptGenerator;
import com.solvd.views.atm.AbstractAtmView;
import java.sql.Timestamp;
import java.time.Instant;

public interface IFeatureController {

    void run();

    default void logEvent(Card card, EnumEventNames eventName) {
        Event event = new Event();
        event.setCard(card);
        event.setDatetime(Timestamp.from(Instant.now()));
        event.setEventType(new EventTypeService().getEventTypeByTypeName(eventName.getEventName()));
        new EventService().insert(event);
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
                ReceiptGenerator.createReceipt(transaction);
                view.displayBody("Printing Receipt..");
                break;
            } else if (userSel == 2) {
                break;
            }
        }
        view.displayBody("Returning to main menu..");
    }

}
