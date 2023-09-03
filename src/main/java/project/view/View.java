package project.view;

import java.util.List;

public interface View {

    /**
     * Method to start the view.
     */
    void start();

    /**
     * Method that to start the connection with the database.
     */
    void startConnection();

    /**
     * Method to show in the view the names of the tables.
     * @param names table' names
     */
    void loadTableNames(List<String> names);

    /**
     * Method to show the results of SELECT queries.
     * @param columns columns' names, i. e. the names of the fields in the table
     * @param results a list of results, i. e. the result of the query
     */
    void viewTable(List<String> columns, List<List<String>> results);

    /**
     * Method to show the correctness of a previously tried insert.
     * @param insertCorrect true if the insert is correct, else false
     */
    void printControlMessage(boolean insertCorrect);

    /**
     * Method to show the names of the values to insert to run correctly the query.
     * @param values values to run the query
     */
    void viewQueryValues(List<String> values);

    /**
     * Setter for columns of the table selected. 
     * It can be used to show the elements of the passed list to help the user to insert the values.
     * @param columns names of the selected table's columns
     */
    void setColumnsNames(List<String> columns);
    
}
