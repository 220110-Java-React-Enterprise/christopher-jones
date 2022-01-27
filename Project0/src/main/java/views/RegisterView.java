package views;

import persistence.UserModel;
import persistence.UserRepo;
import utilities.ContextSave;
import utilities.ViewManager;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterView extends View {

    public RegisterView() {
        viewName = "register";
        viewManager = ViewManager.getViewManager();
    }


    @Override
    public void renderView() throws SQLException, IOException {
        String username = "";
        String email = "";
        Boolean validCheck = false;
        Boolean uniqueCheck = false;
        UserRepo repo = new UserRepo();
        System.out.println("Registration for New Users\n" +
                "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\n");
        while(validCheck == false) {
            System.out.print("Please enter your email: ");
            email = viewManager.getScanner().nextLine();
            validCheck = repo.validEmail(email);
            if(validCheck == false) {
                System.out.println("\nPlease enter an unregistered and valid email\n\n\n");
            }
        }
        while (uniqueCheck == false) {
            System.out.print("\nPlease enter your desired username: ");
            username = viewManager.getScanner().nextLine();
            uniqueCheck = repo.uniqueUsername(username);
            if (uniqueCheck == false) {
                System.out.println("\nThat username is currently taken, please try another\n\n\n");
            }
        }
                System.out.print("\nPlease enter your new password: ");
                String password = viewManager.getScanner().nextLine();

                UserModel newUser = new UserModel(email, username, password);
                newUser.setUserId(repo.create(newUser));

                ContextSave.setCurrentUser(newUser);

                viewManager.navigate("account");

    }
}
