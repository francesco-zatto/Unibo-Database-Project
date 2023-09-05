package project.db.api.query_runner;

import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import project.db.api.RestaurantQuery;
import project.query.Table;
import project.tableFactory.StaticTableFactory;

/**
 * Class to run Inserire Conto query.
 */
public class InserireContoRunner implements RestaurantQueryRunner {

    /**
     * {@inheritDoc}
     */
    @Override
    public Table runQuery(Connection connection, List<String> values) throws SQLException {
        String queryString = RestaurantQuery.INSERIRE_CONTO_A_PRENOTAZIONE_SALVATA.getQueryText();
        executeStatement(connection, values, queryString);
        return StaticTableFactory.getEmptyTable();
    }

    private void executeStatement(Connection connection, List<String> values, String queryString) throws SQLException {
        PreparedStatement statement;
        statement = connection.prepareStatement(queryString);
        statement.setObject(1, values.get(0), JDBCType.INTEGER);
        statement.setObject(2, values.get(1), JDBCType.CHAR);
        statement.executeUpdate();
    }
    
}
