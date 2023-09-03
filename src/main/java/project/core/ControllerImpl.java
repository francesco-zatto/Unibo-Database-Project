package project.core;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import project.db.api.ConnectionProvider;
import project.db.api.RestaurantQuery;
import project.query.Database;
import project.query.DatabaseImpl;
import project.query.Record;
import project.query.RecordImpl;
import project.query.Table;
import project.view.SwingView;
import project.view.View;

/**
 * Implementation of Controller that allows interactions between implementations of Database and of View.
 */
public class ControllerImpl implements Controller {

    private static final String DATABASE_NAME = "restaurant";
    private final View view = new SwingView(this);
    private Database database;
    private ConnectionProvider connectionProvider;
    private Optional<Connection> connection = Optional.empty();

    /**
     * Constructor that starts the view.
     */
    public ControllerImpl() {
        this.view.start();
    }

    /**
     * {@inheritDoc}
     */
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

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadTableNames() {
        this.view.loadTableNames(this.database.getTableNames());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadTable(String name) {
        var table = this.database.getTable(name);
        viewTable(table);
    }

    private void viewTable(final Table table) {
        var columnsList = table.getColumnsAsList();
        var tableRecords = mapFromRecordsToLists(table);
        this.view.viewTable(columnsList, tableRecords);
    }

    private List<List<String>> mapFromRecordsToLists(final Table table) {
        return table.getRecords().stream()
                .map(Record::getElements)
                .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertInTable(List<String> elements, String table) {
        this.view.printControlMessage(this.database.insertInTable(new RecordImpl(elements), table));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadQueryValues(RestaurantQuery query) {
        this.view.viewQueryValues(this.database.getQueryValues(query));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void runQuery(final RestaurantQuery query, final List<String> values) {
        var table = this.database.runQuery(query, values);
        if (table.isPresent()) {
            viewTable(table.get());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadColumnsNames(final String table) {
        this.view.setColumnsNames(this.database.getColumnNames(table));
    }
    
}
