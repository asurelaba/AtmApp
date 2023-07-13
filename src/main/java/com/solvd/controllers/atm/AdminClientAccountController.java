package com.solvd.controllers.atm;

import com.solvd.EnumEventNames;
import com.solvd.controllers.icontrollers.atm.IAdminClientAccountController;
import com.solvd.db.model.Account;
import com.solvd.db.model.Card;
import com.solvd.services.AccountService;
import com.solvd.services.UserService;
import com.solvd.views.atm.AdminClientAccountView;
import org.apache.commons.lang3.StringUtils;

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
        view.displayAccountMenu();
        int userSel = view.getUserSelectionWithThreeChoices();

        switch (userSel) {
            case 1 -> createAccount();
            case 2 -> getAccountId();
            case 3 -> viewAccounts();
            default -> view.displayBody("Invalid selection");
        }
    }

    public void getAccountId() {
        int userInput = view.getUserInputWithInteger("Enter user account ID:");
        validateAccount(userInput);
    }

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

        exitRun(view);
    }

    public void deleteAccount() {
        accountService.delete(accountId);
        view.displayBody("Account with ID " + accountId + " deleted successfully!");
        logEvent(adminCard, EnumEventNames.ACCOUNT_REMOVAL);

        exitRun(view);
    }

    public void viewAccounts() {
        displayAccounts(accountService.getAll());
        logEvent(adminCard, EnumEventNames.ACCOUNTS_QUERY);
        exitRun(view);
    }

    public void validateAccount(int userInput) {
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
        int columnWidth = screenWidth / 8;
        view.display("-".repeat(screenWidth));
        view.display("|" + centerAndTrim("ACCOUNT ID", columnWidth) + "|" +
                centerAndTrim("ROUTING NUMBER", columnWidth) + "|" +
                centerAndTrim("BALANCE", columnWidth) + "|" +
                centerAndTrim("USER ID", columnWidth) + "|" +
                centerAndTrim("STATUS", columnWidth) + "|" +
                centerAndTrim("FIRST NAME", columnWidth) + "|" +
                centerAndTrim("LAST NAME", columnWidth) + "|" +
                centerAndTrim("ROLE", columnWidth) + "|");

        view.display("-".repeat(screenWidth));
        for (Account account : accounts) {
            view.display("|" + centerAndTrim(String.valueOf(account.getAccountId()), columnWidth) + "|" +
                    centerAndTrim(String.valueOf(account.getRoutingNumber()), columnWidth) + "|" +
                    centerAndTrim(String.valueOf(account.getBalance()), columnWidth) + "|" +
                    centerAndTrim(String.valueOf(account.getUser().getUserId()), columnWidth) + "|" +
                    centerAndTrim(account.getUser().getStatus(), columnWidth) + "|" +
                    centerAndTrim(account.getUser().getPerson().getFirstName(), columnWidth) + "|" +
                    centerAndTrim(account.getUser().getPerson().getLastName(), columnWidth) + "|" +
                    centerAndTrim(account.getUser().getUserRole().getName(), columnWidth) + "|");
        }
    }

    public String centerAndTrim(String s, int width) {
        return StringUtils.center(s.substring(0, Math.min(s.length(), width)), width);
    }
}

