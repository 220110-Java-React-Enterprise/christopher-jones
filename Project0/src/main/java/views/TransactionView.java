package views;

import persistence.AccountModel;
import persistence.AccountRepo;
import utilities.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;

public class TransactionView extends View {

    public TransactionView() {
        viewName = "transaction";
        viewManager = ViewManager.getViewManager();
    }

    @Override
    public void renderView() throws SQLException, IOException {
        AccountRepo repo = new AccountRepo();
        CustomLinkedList<AccountModel> list = repo.getAllItemsByUserId(ContextSave.getCurrentUser().getUserId());

        System.out.println("\n-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");
        int i = 1;
        for (AccountModel account : list) {
            System.out.println(i + ") " + account.toString());
            i++;
        }
        System.out.println("-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-_-");

        System.out.println("B) Back\n" +
                "Q) Quit\n");
        System.out.print("Which account is the transaction for? ");

        String input = viewManager.getScanner().nextLine();

        switch (input) {
            case "B":
            case "b":
            case "back":
            case "Back":
                viewManager.navigate("account");
                break;
            case "Q":
            case "q":
            case "quit":
            case "Quit":
                viewManager.quit();
                break;
            default:
                try {
                    Integer selection = Integer.parseInt(input);
                    AccountModel selectedItem = list.get(selection-1);
                    System.out.println("\n\nPlease select the type of transaction\n" +
                            "1) Deposit\n" +
                            "2) Withdrawal");
                    String transactionType = viewManager.getScanner().nextLine();
                    switch (transactionType) {
                        case "1":
                            System.out.print("\n\nHow much would you like to deposit? $");
                            Double depositAmount = viewManager.getScanner().nextDouble();
                            viewManager.getScanner().nextLine();
                            if (depositAmount < 10_000) {
                                selectedItem.setBalance(selectedItem.getBalance() + depositAmount);
                            } else {
                                System.out.println("Not a valid amount");
                            }
                            break;
                        case "2":
                            System.out.print("How much would you like to withdrawal? $");
                            Double withdrawalAmount = viewManager.getScanner().nextDouble();
                            viewManager.getScanner().nextLine();
                            if (withdrawalAmount <= selectedItem.getBalance()) {
                                selectedItem.setBalance(selectedItem.getBalance() - withdrawalAmount);
                                System.out.println("Completing transaction...\n\n\n");
                            } else {
                                System.out.println("Not a valid amount\n\n\n");
                            }
                            break;
                        default:
                            System.out.println("Invalid input, please try again...\n\n\n");
                    }
                    repo.update(selectedItem);


                } catch(NumberFormatException | InputMismatchException e) {
                    System.out.println("\nInvalid input, please try again... \n\n\n");
                }


        }
        viewManager.navigate("account");
    }
}
