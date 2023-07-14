package com.solvd.db.model;

import java.util.Objects;

public class Transaction {

    private int transactionId;
    private double amount;
    private String status;
    private Event event;

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "Transaction{" +
            "transactionId=" + transactionId +
            ", amount=" + amount +
            ", status='" + status + '\'' +
            ", event=" + event +
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
        Transaction that = (Transaction) o;
        return transactionId == that.transactionId && Double.compare(that.amount, amount) == 0
            && Objects.equals(status, that.status) && Objects.equals(event, that.event);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, amount, status, event);
    }

}
