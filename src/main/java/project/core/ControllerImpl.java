package project.core;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import project.db.api.ConnectionProvider;
import project.db.api.RestaurantQuery;
import project.query.Database;
import project.query.DatabaseImpl;
import project.view.SwingView;
import project.view.View;

public class ControllerImpl implements Controller {

    private static final String DATABASE_NAME = "restaurant";
    private final View view = new SwingView(this);
    private Database database;
    private ConnectionProvider connectionProvider;
    private Optional<Connection> connection = Optional.empty();

    public ControllerImpl() {
        this.view.startGUI();
    }

    @Override
    public void tryAccess(final String username, final String password) {
        this.connectionProvider = new ConnectionProvider(username, password, DATABASE_NAME);
        this.connection = this.connectionProvider.getMySQLConnection();
        if (this.connection.isPresent()) {
            startConnection();
        }
    }

    private void startConnection() {
        this.database = new DatabaseImpl(this.connection.get());
        this.view.startConnection();
    }

    @Override
    public void loadTableNames() {
        this.view.loadTableNames(this.database.getTableNames());
    }

    @Override
    public void loadTable(String name) {
        var table = this.database.getTable(name);
        viewTable(table);
    }

    private void viewTable(final List<List<String>> table) {
        var columnsNames = table.get(0);
        var tableResults = table.subList(1, table.size());
        this.view.viewTable(columnsNames, tableResults);
    }

    @Override
    public void insertInTable(List<String> elements, String table) {
        this.view.printControlMessage(this.database.insertInTable(elements, table));
    }

    @Override
    public void loadQueryValues(RestaurantQuery query) {
        this.view.viewQueryValues(this.database.getQueryValues(query));
    }

    @Override
    public void runQuery(final RestaurantQuery query, final List<String> values) {
        var table = this.database.runQuery(query, values);
        if (table.isPresent()) {
            viewTable(table.get());
        }
    }

    @Override
    public void loadColumnsNames(final String table) {
        this.view.setColumnsNames(this.database.getColumnNames(table));
    }
    
}
