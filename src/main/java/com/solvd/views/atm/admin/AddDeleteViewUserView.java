package com.solvd.views.atm.admin;

import com.solvd.db.model.Account;
import com.solvd.db.model.Card;
import com.solvd.db.model.User;
import com.solvd.services.AccountService;
import com.solvd.services.CardService;
import com.solvd.views.atm.AbstractAtmView;
import java.util.InputMismatchException;
import java.util.List;

public class AddDeleteViewUserView extends AbstractAtmView {

    @Override
    public String featureTitle() {
        return "Add/Delete/View Users";
    }

    public void displayUsersMenu() {
        display(featureTitle());
        display("1. Add User");
        display("2. Delete User");
        display("3. View All Users");
        display("0. Exit");
    }

    public String featureTitle(String choice) {
        return switch (choice.toLowerCase()) {
            case "add" -> "Add User";
            case "delete" -> "Delete User";
            case "view" -> "View Users";
            default -> null;
        };
    }

    public String getInputString() {
        while (true) {
            try {
                return s.next("[A-Z|a-z| ]+");
            } catch (InputMismatchException e) {
                displayBody(
                    "Please enter a valid name. Name cannot be empty, contain digits or special characters");
                s.next();
            }
        }
    }

    public String getFirstName() {
        displayBody("Enter User first Name");
        return getInputString();
    }

    public String getLastName() {
        displayBody("Enter User last name");
        return getInputString();
    }

    public String getUserRole() {
        displayBody("Enter user's role 1 - Admin / 2 - Client");
        int inputUserRole;
        do {
            inputUserRole = getUserSelection();
        } while (inputUserRole < 1 || inputUserRole > 2);
        return inputUserRole == 1 ? "Administrator" : "Client";
    }

    public int getUserId() {
        displayBody("Enter Userid: ");
        int userId = 0;
        while (true) {
            try {
                userId = s.nextInt();
                break;
            } catch (InputMismatchException e) {
                display("Invalid Input. UserId accepts only digits");
                s.next();
            }
        }
        return userId;
    }

    public void displaySuccessMessage(String userOption) {
        switch (userOption.toLowerCase()) {
            case "add" -> displayBody("User added successfully");
            case "delete" -> displayBody("User deleted");
        }
    }

    public void displayUsers(List<User> users) {
        int columnWidth = SCREEN_WIDTH / 5;
        display("-".repeat(SCREEN_WIDTH));
        display("|" + centerAndTrim("USERID", columnWidth) + "|" +
            centerAndTrim("FIRSTNAME", columnWidth) + "|" +
            centerAndTrim("LASTNAME", columnWidth) + "|" +
            centerAndTrim("ROLE", columnWidth) + "|" +
            centerAndTrim("ACCOUNT", columnWidth) + "|" +
            centerAndTrim("CARD NUMBER", columnWidth) + "|");
        display("-".repeat(SCREEN_WIDTH));
        for (User user : users) {
            try {
                List<Long> cards = new CardService().getCardsByUserId(user.getUserId()).stream()
                    .map(Card::getCardNumber).toList();
                Account account = new AccountService().getAccountByUserId(user.getUserId());

                String record =
                    "|" + centerAndTrim(String.valueOf(user.getUserId()), columnWidth) + "|" +
                        centerAndTrim(user.getPerson().getFirstName(), columnWidth) + "|" +
                        centerAndTrim(user.getPerson().getLastName(), columnWidth) + "|" +
                        centerAndTrim(user.getUserRole().getName(), columnWidth) + "|" +
                        centerAndTrim(String.valueOf(
                                account == null ? " ".repeat(columnWidth) : account.getAccountId()),
                            columnWidth) + "|";

                if (cards.isEmpty()) {
                    display(record);
                } else {
                    display(
                        record + centerAndTrim(String.valueOf(cards.get(0)), columnWidth) + "|");
                }
                if (cards.size() > 1) {
                    for (int i = 1; i < cards.size(); i++) {
                        display("|" + " ".repeat(record.length()) + centerAndTrim(
                            String.valueOf(cards.get(i)), columnWidth) + "|");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
