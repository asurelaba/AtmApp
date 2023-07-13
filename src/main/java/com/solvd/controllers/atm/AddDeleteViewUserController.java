package com.solvd.controllers.atm;

import com.solvd.enums.EnumEventName;
import com.solvd.controllers.icontrollers.IFeatureController;
import com.solvd.db.model.Card;
import com.solvd.db.model.Person;
import com.solvd.db.model.User;
import com.solvd.services.PersonService;
import com.solvd.services.UserRoleService;
import com.solvd.services.UserService;
import com.solvd.views.atm.AddDeleteViewUserView;

import java.util.List;

public class AddDeleteViewUserController implements IFeatureController {

    Card card;
    AddDeleteViewUserView view = new AddDeleteViewUserView();

    public AddDeleteViewUserController(Card card) {
        this.card = card;
    }

    @Override
    public void run() {
        boolean stop = false;
        while (!stop) {
            view.displayUsersMenu();
            int choice = view.getUserSelection();
            switch (choice) {
                case 1 -> handleAddUser();
                case 2 -> handleDeleteUser();
                case 3 -> viewAllUsers();
                case 0 -> {
                    stop = true;
                }
                default -> view.display("Invalid selection");
            }
        }
    }

    public void handleAddUser() {
        String userOption = "add";
        view.displayTitle(view.featureTitle(userOption));

        String firstName = view.getFirstName();
        String lastName = view.getLastName();
        String userRole = view.getUserRole();

        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        new PersonService().insert(person);

        User user = new User();
        user.setPerson(person);
        user.setStatus("active");
        user.setUserRole(new UserRoleService().getUserRoleByRoleName(userRole));
        new UserService().insert(user);

        view.displaySuccessMessage(userOption);
        logEvent(card, EnumEventName.USER_CREATION);
        view.display("Returning to Users Menu");
    }

    public void handleDeleteUser() {
        String userOption = "delete";
        view.displayTitle(view.featureTitle(userOption));

        while (true) {
            int userId = view.getUserId();
            UserService userService = new UserService();
            User user = userService.getById(userId);
            if (user != null) {
                userService.delete(userId);
                new PersonService().delete(user.getPerson().getPersonId());
                view.displaySuccessMessage(userOption);
                logEvent(card, EnumEventName.USER_REMOVAL);
                view.display("Returning to Users Menu");
                break;
            } else {
                view.displayBody("User Id does not exist.");
                int choice;
                do {
                    view.displayBody("Please enter 1. To enter another userid \n 2. To return to Add/Delete/View Users Menu");
                    choice = view.getUserSelection();
                } while (choice < 1 || choice > 2);
                if (choice == 2) {
                    break;
                }
            }
        }
    }

    public void viewAllUsers() {
        String userOption = "view";
        view.displayTitle(view.featureTitle(userOption));
        List<User> users = new UserService().getAll();
        view.displayUsers(users);
        exitRun(view);
    }
}
