package com.solvd.db.model;

import java.util.Objects;

public class Card {

    private int cardId;
    private long cardNumber;
    private int pin;
    private String status;
    private CardType cardType;
    private User user;

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CardType getCardType() {
        return cardType;
    }

    public void setCardType(CardType cardType) {
        this.cardType = cardType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", cardNumber=" + cardNumber +
                ", pin=" + pin +
                ", status='" + status + '\'' +
                ", cardType=" + cardType +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardId == card.cardId && cardNumber == card.cardNumber && pin == card.pin && Objects.equals(status, card.status) && Objects.equals(cardType, card.cardType) && Objects.equals(user, card.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardId, cardNumber, pin, status, cardType, user);
    }
}
