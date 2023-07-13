package com.solvd;

public enum EnumEventNames {
    LOG_IN("Log In"),
    LOG_OUT("Log Out"),
    LOCK_CARD("Lock Card"),
    TRANSACTION_QUERY("Transaction Query"), // Use to log when a transaction is queried.
    PRINT_RECEIPT("Print Receipt"),
    CHANGE_PIN("Change Pin"),
    UNLOCK_CARD_REQUEST("Unlock Card Request"),
    WITHDRAWAL("Withdrawal"),
    DEPOSIT("Deposit"),
    TRANSFER("Transfer"),
    BALANCE_INQUIRY("Balance Inquiry"),
    USER_CREATION("User Creation"),
    ACCOUNT_CREATION("Account Creation"),
    CARD_CREATION("Card Creation"),
    ADMIN_CREATION("Admin Creation"),
    USER_REMOVAL("User Removal"),
    ACCOUNT_REMOVAL("Account Removal"),
    CARD_REMOVAL("Card Removal"),
    ADMIN_REMOVAL("Admin Removal"),
    UNLOCK_CARD("Unlock Card"),
    BALANCE_ADJUSTMENT("Balance Adjustment"),
    APPROVED("approved"),
    ACCOUNTS_QUERY("Accounts Query");

    private final String eventName;

    EnumEventNames(String eventName) {
        this.eventName = eventName;
    }

    public String getEventName() {
        return eventName;
    }
}