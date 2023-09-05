package project.db.api.query_runner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import project.query.Record;
import project.query.RecordImpl;
import project.query.Table;
import project.tableFactory.StaticTableFactory;

/**
 * Abstract class that has the template method.
 * It should be extended by Runners for Select queries for DRY principle.
 */
public abstract class AbstractRestaurantQueryRunner implements RestaurantQueryRunner {

    /**
     * {@inheritDoc}
     */
    @Override
    public Table runQuery(Connection connection, List<String> values) throws SQLException {
        String queryString = getQueryText();
        List<Record> recordList = new LinkedList<>();
        recordList.add(new RecordImpl(getColumnsNames()));
        PreparedStatement statement = connection.prepareStatement(queryString);
        ResultSet resultSet = getResultSet(values, statement);
        while (resultSet.next()) {
            recordList.add(getCurrentRecord(resultSet));
        }
        return StaticTableFactory.createTable(recordList);
    }

    protected abstract String getQueryText();

    protected abstract List<String> getColumnsNames();

    protected abstract ResultSet getResultSet(List<String> values, PreparedStatement statement) throws SQLException;

    protected abstract Record getCurrentRecord(ResultSet resultSet) throws SQLException;
    
}
