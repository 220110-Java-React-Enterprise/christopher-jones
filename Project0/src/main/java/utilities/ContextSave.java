package utilities;

import persistence.UserModel;

public class ContextSave {
    private static UserModel currentUser;

    public static void setCurrentUser(UserModel user) {
        currentUser = user;
    }

    public static UserModel getCurrentUser() {
        return currentUser;
    }

}