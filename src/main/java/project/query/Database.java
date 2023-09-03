package project.query;

import java.util.List;
import java.util.Optional;

import project.db.api.RestaurantQuery;

/**
 * Interface to allow interactions with a database.
 */
public interface Database {

    /**
     * Getter for the names of the database's tables.
     * @return tables' names
     */
    List<String> getTableNames();

    /**
     * Getter for a database's table given its name.
     * @param tableName table's name
     * @return table with its records
     */
    Table getTable(String tableName);

    /**
     * Method to try inserting a record in a given database's table
     * @param record record to insert
     * @param table table's name
     * @return the correctness of the insertion
     */
    boolean insertInTable(Record record, String table);

    /**
     * Getter for the required values to run the passed query.
     * @param query query to get the required values
     * @return a list with the required values
     */
    List<String> getQueryValues(RestaurantQuery query);

    /**
     * Method to run the given query using the given values.
     * @param query query to run
     * @param values values used in the execution
     * @return result of the query, that can be present or not
     */
    Optional<Table> runQuery(RestaurantQuery query, List<String> values);

    /**
     * Getter for the given table's columns.
     * @param tableName table's name
     * @return a list with the names of the columns
     */
    List<String> getColumnNames(String tableName);

}