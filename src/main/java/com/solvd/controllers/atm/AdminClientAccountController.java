package com.solvd.controllers.atm;

import com.solvd.enums.EnumEventNames;
import com.solvd.controllers.icontrollers.atm.IAdminClientAccountController;
import com.solvd.db.model.Account;
import com.solvd.db.model.Card;
import com.solvd.services.AccountService;
import com.solvd.services.UserService;
import com.solvd.views.atm.AdminClientAccountView;

import java.util.List;

public class AdminClientAccountController implements IAdminClientAccountController {

    private int accountId;
    private Card adminCard;
    private AccountService accountService;
    private UserService userService;
    protected final AdminClientAccountView view = new AdminClientAccountView();

    public AdminClientAccountController(Card adminCard) {
        this.adminCard = adminCard;
        this.accountService = new AccountService();
        this.userService = new UserService();
    }

    @Override
    public void run() {
        while (true) {
            view.displayAccountMenu();
            int userSel = view.getUserSelectionWithFourChoices();

            switch (userSel) {
                case 1 -> createAccount();
                case 2 -> getAccountId();
                case 3 -> viewAccounts();
                case 4 -> {
                    return;
                }
                default -> view.displayBody("Invalid selection");
            }
        }
    }

    @Override
    public void getAccountId() {
        int userInput = view.getUserInputWithInteger("Enter user account ID:");
        validateAndDeleteAccount(userInput);
    }

    @Override
    public void createAccount() {
        int routingNumber = view.getUserInputWithInteger("Enter routing number:");
        double balance = view.getBalance();
        int userId = view.getUserInputWithInteger("Enter user ID:");

        if (validateUser(userId)) {
            Account newAccount = new Account();
            newAccount.setRoutingNumber(routingNumber);
            newAccount.setBalance(balance);
            newAccount.setUser(userService.getById(userId));

            accountService.insert(newAccount);
            view.displayBody("New account created successfully with ID: " + newAccount.getAccountId());
            logEvent(adminCard, EnumEventNames.ACCOUNT_CREATION);
        }
    }

    @Override
    public void deleteAccount() {
        accountService.delete(accountId);
        view.displayBody("Account with ID " + accountId + " deleted successfully!");
        logEvent(adminCard, EnumEventNames.ACCOUNT_REMOVAL);
    }

    @Override
    public void viewAccounts() {
        displayAccounts(accountService.getAll());
        logEvent(adminCard, EnumEventNames.ACCOUNTS_QUERY);
    }

    @Override
    public void validateAndDeleteAccount(int userInput) {
        if (accountService.getById(userInput) != null) {
            accountId = userInput;
            deleteAccount();
        } else {
            view.displayNonexistentChoices();
            int userSel = view.getUserSelectionWithTwoChoices();

            if (userSel == 2) {
                getAccountId();
            }
        }
    }

    @Override
    public boolean validateUser(int userId) {
        if (userService.getById(userId) == null) {
            view.displayBody("User does not exist.");

            return false;
        }

        if (accountService.getAccountByUserId(userId) != null) {
            view.displayBody("An account already exists for this user. Account ID: " +
                    accountService.getAccountByUserId(userId).getAccountId());

            return false;
        }

        return true;
    }

    @Override
    public void displayAccounts(List<Account> accounts) {
        int screenWidth = 100;
        int columnWidth = screenWidth / 7;
        view.display("-".repeat(screenWidth));
        view.display("|" + view.centerAndTrim("ACCOUNT ID", columnWidth) + "|" +
                view.centerAndTrim("ROUTING NUMBER", columnWidth) + "|" +
                view.centerAndTrim("BALANCE", columnWidth) + "|" +
                view.centerAndTrim("USER ID", columnWidth) + "|" +
                view.centerAndTrim("FIRST NAME", columnWidth) + "|" +
                view.centerAndTrim("LAST NAME", columnWidth) + "|" +
                view.centerAndTrim("ROLE", columnWidth) + "|");

        view.display("-".repeat(screenWidth));
        for (Account account : accounts) {
            view.display("|" + view.centerAndTrim(String.valueOf(account.getAccountId()), columnWidth) + "|" +
                    view.centerAndTrim(String.valueOf(account.getRoutingNumber()), columnWidth) + "|" +
                    view.centerAndTrim(String.valueOf(account.getBalance()), columnWidth) + "|" +
                    view.centerAndTrim(String.valueOf(account.getUser().getUserId()), columnWidth) + "|" +
                    view.centerAndTrim(account.getUser().getPerson().getFirstName(), columnWidth) + "|" +
                    view.centerAndTrim(account.getUser().getPerson().getLastName(), columnWidth) + "|" +
                    view.centerAndTrim(account.getUser().getUserRole().getName(), columnWidth) + "|");
        }
    }

}

