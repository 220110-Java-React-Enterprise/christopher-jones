package views;

import persistence.UserModel;
import persistence.UserRepo;
import utilities.ContextSave;
import utilities.ViewManager;

import java.io.IOException;
import java.sql.SQLException;

public class LoginView extends View {

    public LoginView() {
        viewName = "login";
        viewManager = ViewManager.getViewManager();
    }

    @Override
    public void renderView() throws SQLException, IOException {

        System.out.println("\n\n\nUser Login\n-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");


        System.out.print("\nEnter username: ");
        String username =  viewManager.getScanner().nextLine();

        System.out.print("\nEnter password: ");
        String password =  viewManager.getScanner().nextLine();

        UserRepo repo = new UserRepo();
        UserModel user = repo.authenticate(username, password);

        if (user == null) {
            System.out.println("\nIncorrect credentials... \n\n\n");
            viewManager.navigate("welcome");
            return;
        }

        ContextSave.setCurrentUser(user);
        viewManager.navigate("account");
    }
}
