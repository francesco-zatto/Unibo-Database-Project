package project.core;

import java.util.List;

import project.db.api.RestaurantQuery;

/**
 * Interface for the controller of the database's app.
 */
public interface Controller {

    /**
     * Method that try a connection with the database using the credentials passed as arguments.
     * @param username username to login
     * @param password password to login, if correct the access is granted
     */
    void tryAccess(String username, String password);

    /**
     * Method to load from the database the tables' names and then to show them in the view.
     */
    void loadTableNames();

    /**
     * Method to load the content of a table, whose name is passed as argument.
     * @param name table's name
     */
    void loadTable(String name);

    /**
     * Method to insert a record by the view in to the table.
     * @param elements elements to insert as a record
     * @param table table's name
     */
    void insertInTable(List<String> elements, String table);

    /**
     * Method to load the names of the values to insert given a query.
     * @param query query to load its required values
     */
    void loadQueryValues(RestaurantQuery query);

    /**
     * Method that, given a query and the inserted values by the user, tries to run it.
     * @param query query to run
     * @param values values used to run correctly the query
     */
    void runQuery(RestaurantQuery query, List<String> values);

    /**
     * Method to load the names of a given table. It can be used to show them in the view.
     * @param table table to show its columns' names
     */
    void loadColumnsNames(String table);

}