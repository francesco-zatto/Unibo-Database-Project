package project.db.api.query_runner;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import project.query.Table;

/**
 * Interface to be implemented with the execution of one of the restaurant query.
 */
public interface RestaurantQueryRunner {
    
    /**
     * Method that execute a restaurant query with the given values.
     * @param connection connection to database
     * @param values values to put inside the query
     * @return table representing the result of the query
     */
    Table runQuery(Connection connection, List<String> values) throws SQLException;

}
