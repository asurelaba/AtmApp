package com.solvd.db.model;

import java.util.Objects;

public class Account {

    private int accountId;
    private int routingNumber;
    private double balance;
    private User user;

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getRoutingNumber() {
        return routingNumber;
    }

    public void setRoutingNumber(int routingNumber) {
        this.routingNumber = routingNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", routingNumber=" + routingNumber +
                ", balance=" + balance +
                ", user=" + user +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountId == account.accountId && routingNumber == account.routingNumber && Double.compare(account.balance, balance) == 0 && Objects.equals(user, account.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, routingNumber, balance, user);
    }
}
