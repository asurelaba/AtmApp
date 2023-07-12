package com.solvd.views.atm;

import com.solvd.db.model.User;
import org.apache.commons.lang3.StringUtils;

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
        int columnWidth = screenWidth / 4;
        display("-".repeat(screenWidth));
        display("|" + centerAndTrim("USERID", columnWidth) + "|" +
                centerAndTrim("FIRSTNAME", columnWidth) + "|" +
                centerAndTrim("LASTNAME", columnWidth) + "|" +
                centerAndTrim("ROLE", columnWidth) + "|" );
        display("-".repeat(screenWidth));
        for (User user : users) {
            display("|" + centerAndTrim(String.valueOf(user.getUserId()), columnWidth) + "|" +
                    centerAndTrim(user.getPerson().getFirstName(), columnWidth) + "|" +
                    centerAndTrim(user.getPerson().getLastName(), columnWidth) + "|" +
                    centerAndTrim(user.getUserRole().getName(), columnWidth) + "|");
        }
    }

    private String centerAndTrim(String s, int width) {
        return StringUtils.center(s.substring(0, Math.min(s.length(), width)), width);
    }
}