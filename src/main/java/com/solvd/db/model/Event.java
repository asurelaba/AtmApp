package com.solvd.db.model;

import java.sql.Timestamp;
import java.util.Objects;

public class Event {

    private int eventId;
    private Timestamp datetime;
    private Card card;
    private EventType eventType;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    @Override
    public String toString() {
        return "Event{" +
            "eventId=" + eventId +
            ", datetime=" + datetime +
            ", card=" + card +
            ", eventType=" + eventType +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Event event = (Event) o;
        return eventId == event.eventId && Objects.equals(datetime, event.datetime)
            && Objects.equals(card, event.card) && Objects.equals(eventType, event.eventType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, datetime, card, eventType);
    }

}
