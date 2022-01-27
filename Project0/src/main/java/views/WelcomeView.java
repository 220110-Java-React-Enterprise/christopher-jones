package views;

import utilities.ViewManager;

import java.io.IOException;
import java.sql.SQLException;

public class WelcomeView extends View {
    public WelcomeView() {
        viewName = "welcome";
        viewManager = ViewManager.getViewManager();
    }

    @Override
    public void renderView() throws SQLException, IOException {
        System.out.println("WELCOME TO ZILLEI BANKING\n" +
                "Where your money is our money\n" +
                "-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-\n" +
                "Please enter your desired selection:\n" +
                "1) REGISTER\n" +
                "\tIf you are a new user, type \"1\" to create an account.\n"+
                "2) LOGIN\n" +
                "\tIf you already have a Zillei banking account, type \"2\" to login.\n" +
                "Q) QUIT\n" +
                "\tIf you wanted to open a more exciting app, type \"Q\" to quit.");

        String input = viewManager.getScanner().nextLine();

        switch (input) {
            case "1":
            case "REGISTER":
            case "Register":
            case "register":
                viewManager.navigate("register");
                break;
            case "2":
            case "LOGIN":
            case "Login":
            case "login":
                viewManager.navigate("login");
                break;
            case "Q":
            case "q":
                viewManager.quit();
                break;
            default:
                System.out.println("\nInvalid input, please try again...\n\n\n");
                break;
        }
    }
}
