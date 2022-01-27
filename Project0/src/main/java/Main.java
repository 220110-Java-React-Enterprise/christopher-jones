import utilities.ConnectionManager;
import utilities.CustomArrayList;
import utilities.CustomLinkedList;
import utilities.ViewManager;
import views.*;

import java.sql.Connection;

public class Main {
    public static void main(String ...args) {

        ViewManager.getViewManager().registerView(new WelcomeView());
        ViewManager.getViewManager().registerView(new RegisterView());
        ViewManager.getViewManager().registerView(new LoginView());
        ViewManager.getViewManager().registerView(new AccountView());
        ViewManager.getViewManager().registerView(new NewAccountView());
        ViewManager.getViewManager().registerView(new TransactionView());

        try {
            Connection conn = ConnectionManager.getConnection();

            ViewManager.getViewManager().navigate("welcome");
            while(ViewManager.getViewManager().isRunning()) {
                ViewManager.getViewManager().render();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
