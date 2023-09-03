package project.db.api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Expose a utility method to connect to a MySQL database 
 */
public final class ConnectionProvider {

    private final String username;
    private final String password;
    private final String dbName;

    /**
     * Constructor for a connection provider.
     * @param username the username used to connect to the database
     * @param password the password used to connect to the database
     * @param dbName the name of the database to connect to
     */
    public ConnectionProvider (final String username, final String password, final String dbName) {
        this.username = username;
        this.password = password;
        this.dbName = dbName;
    }

    /**
     * Method to try to get a connection with the MySQL database.
     * @return a Connection with the database specified in the class constructor
     * @throws IllegalStateException if the connection could not be establish
     */
    public Optional<Connection> getMySQLConnection() {
        final String dbUri = "jdbc:mysql://localhost:3306/" + this.dbName;
        try {
            // Thanks to the JDBC DriverManager we can get a connection to the database
            return Optional.of(DriverManager.getConnection(dbUri, this.username, this.password));
        } catch (final SQLException e) {
            return Optional.empty();
        }
    }

}
