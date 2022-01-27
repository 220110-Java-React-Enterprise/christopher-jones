package views;

import persistence.AccountModel;
import persistence.AccountRepo;
import utilities.ContextSave;
import utilities.ViewManager;

import java.io.IOException;
import java.sql.SQLException;

public class NewAccountView extends View {

    public NewAccountView() {
        viewName = "newAccount";
        viewManager = ViewManager.getViewManager();
    }

    @Override
    public void renderView() throws SQLException, IOException {
        System.out.println("\n\n\nCreating new account");
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
        System.out.println("Please select account type: ");
        System.out.println("1) Checking\n" +
                "2) Savings");

        String selection = viewManager.getScanner().nextLine();
        String accountTypeSelect = null;
        if (selection.equals("1")) {
            accountTypeSelect = "Checking";
        } else if (selection.equals("2")) {
            accountTypeSelect = "Savings";
        }
        if (accountTypeSelect != null) {
            AccountModel account = new AccountModel(0.00, accountTypeSelect, ContextSave.getCurrentUser().getUserId());
            AccountRepo repo = new AccountRepo();
            repo.create(account);

            System.out.println("Creating Account...\n\n\n");

            viewManager.navigate("account");
        } else {
            System.out.println("Invalid selection, please try again\n\n\n");
        }
    }
}