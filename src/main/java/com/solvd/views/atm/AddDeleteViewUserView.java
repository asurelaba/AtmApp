package com.solvd.views.atm;

import com.solvd.db.model.User;

import java.util.InputMismatchException;
import java.util.List;

public class AddDeleteViewUserView extends AbstractAtmView {
    @Override
    public String featureTitle() {
        return "Add/Delete/View Users";
    }

    public void displayUsersMenu() {
        display(System.lineSeparator());
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
        return s.next();
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
        int screenWidth = 100;
        String columnSpacer = " ".repeat(10);
        display("-".repeat(screenWidth));
        display("USERID             | FIRSTNAME             | LASTNAME          | ROLE");
        display("-".repeat(screenWidth));
        for (User user : users) {
            display(user.getUserId() + columnSpacer + user.getPerson().getFirstName() + columnSpacer
                    + user.getPerson().getLastName() + columnSpacer + user.getUserRole().getName());
        }
    }
}
