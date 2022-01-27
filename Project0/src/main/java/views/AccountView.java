package views;

import persistence.AccountModel;
import persistence.AccountRepo;
import utilities.ContextSave;
import utilities.CustomLinkedList;
import utilities.ViewManager;

import java.io.IOException;
import java.sql.SQLException;

public class AccountView extends View {

    public AccountView() {
        viewName = "account";
        viewManager = ViewManager.getViewManager();
    }

    @Override
    public void renderView() throws SQLException, IOException {
        System.out.println("Accounts for: " + ContextSave.getCurrentUser().getUsername());
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
        AccountRepo repo = new AccountRepo();
        CustomLinkedList<AccountModel> list = repo.getAllItemsByUserId(ContextSave.getCurrentUser().getUserId());

        int i = 1;
        for (AccountModel account : list) {
            System.out.println(i + ") " + account.toString());
            i++;
        }
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");

        System.out.println("Please select one of the following options\n" +
                "1) Create account.\n" +
                "\tTo add an additional account, type \"1\" or \"new\"\n" +
                "2) Complete a transaction.\n" +
                "\tTo initiate a deposit or withdrawal, type \"2\" or \"transaction\"\n" +
                "Q) Quit.");

        String input = viewManager.getScanner().nextLine();

        switch (input) {
            case "1":
            case "new":
            case "create":
            case "account":
                viewManager.navigate("newAccount");
                break;
            case "2":
            case "transaction":
            case "deposit":
            case "withdrawal":
                viewManager.navigate("transaction");
                break;
            case "Q":
            case "q":
                viewManager.quit();
                break;
            default:
                System.out.println("\nNot a valid input, please try again... \n\n\n");
                break;
        }

    }
}
