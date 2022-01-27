package views;

import utilities.ViewManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Formatter;

public abstract class View {

    protected String viewName;
    protected ViewManager viewManager;

    public String getViewName() {
        return viewName;
    }

    public abstract void renderView() throws SQLException, IOException;
}
