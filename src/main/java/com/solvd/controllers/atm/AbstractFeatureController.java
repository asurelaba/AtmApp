package com.solvd.controllers.atm;

import com.solvd.EnumEventNames;
import com.solvd.controllers.icontrollers.IFeatureController;
import com.solvd.db.model.Card;
import com.solvd.db.model.Event;
import com.solvd.services.EventService;
import com.solvd.services.EventTypeService;
import com.solvd.views.atm.AbstractAtmView;
import java.sql.Timestamp;
import java.time.Instant;

public abstract class AbstractFeatureController implements IFeatureController {

    @Override
    public abstract void run();

    protected void logEvent(Card card, EnumEventNames eventName) {
        Event event = new Event();
        event.setCard(card);
        event.setDatetime(Timestamp.from(Instant.now()));
        event.setEventType(new EventTypeService().getEventTypeByTypeName(eventName.getEventName()));
        new EventService().insert(event);
    }

    protected void exitRun(AbstractAtmView view) {
        view.displayExit();
        while (true) {
            int userSel = view.getUserSelection();
            if (userSel == 0) {
                break;
            }
        }
    }

}

