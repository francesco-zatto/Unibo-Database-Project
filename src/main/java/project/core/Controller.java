package project.core;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import project.db.api.ConnectionProvider;
import project.db.api.RestaurantQuery;
import project.view.SwingView;
import project.view.View;

public class Controller {

    private static final String DATABASE_NAME = "restaurant";
    private final View view = new SwingView(this);
    private Database database;
    private ConnectionProvider connectionProvider;
    private Optional<Connection> connection = Optional.empty();

    public Controller() {
        this.view.startGUI();
    }

    public boolean tryAccess(String username, String password) {
        this.connectionProvider = new ConnectionProvider(username, password, DATABASE_NAME);
        this.connection = this.connectionProvider.getMySQLConnection();
        if (this.connection.isPresent()) {
            this.database = new Database(this.connection.get());
        }
        return this.connection.isPresent();
    }

    public void loadTableNames() {
        this.view.loadTableNames(this.database.getTableNames());
    }

    public void loadTable(String name) {
        var table = this.database.getTable(name);
        this.view.viewTable(table.get(0), table.subList(1, table.size()));
    }

    public Optional<List<List<String>>> runQuery(RestaurantQuery query) {
        return null; //TODO
    }
    
}
