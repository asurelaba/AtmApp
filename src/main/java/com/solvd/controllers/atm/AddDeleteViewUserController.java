package com.solvd.controllers.atm;

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
        view.displayUsersMenu();
        int choice = view.getUserSelection();
        switch (choice) {
            case 1 -> handleAddUser();
            case 2 -> handleDeleteUser();
            case 3 -> viewAllUsers();
            case 0 -> view.display("\n");
            default -> view.display("Invalid selection");
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
        view.displayExit();
    }

    public void handleDeleteUser() {
        String userOption = "delete";
        view.displayTitle(view.featureTitle(userOption));
        int userId = view.getUserId();
        new UserService().delete(userId);
        view.displaySuccessMessage(userOption);
        view.displayExit();
    }

    public void viewAllUsers() {
        String userOption = "view";
        view.displayTitle(view.featureTitle(userOption));
        List<User> users = new UserService().getAll();
        view.displayUsers(users);
        view.displayExit();
    }
}
