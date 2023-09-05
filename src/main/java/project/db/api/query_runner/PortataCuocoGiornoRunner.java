package project.db.api.query_runner;

import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import project.db.api.RestaurantQuery;
import project.query.Record;
import project.query.RecordImpl;
import project.query.Table;
import project.tableFactory.StaticTableFactory;

/**
 * Class to run to view the Portata cooked by a Cuoco in a Giorno.
 */
public class PortataCuocoGiornoRunner implements RestaurantQueryRunner{

    private static final List<String> RESULT_LIST = List.of("Nome Portata");

    /**
     * {@inheritDoc}
     */
    @Override
    public Table runQuery(Connection connection, List<String> values) throws SQLException {
        String queryString = RestaurantQuery.VISUALIZZARE_PORTATA_CUOCO_GIORNO.getQueryText();
        List<Record> recordList = new LinkedList<>();
        recordList.add(new RecordImpl(RESULT_LIST));
        PreparedStatement statement = connection.prepareStatement(queryString);
        ResultSet resultSet = getResultSet(values, statement);
        while (resultSet.next()) {
            recordList.add(getCurrentRecord(resultSet));
        }
        return StaticTableFactory.createTable(recordList);
    }

    private ResultSet getResultSet(List<String> values, PreparedStatement statement) throws SQLException {
        statement.setObject(1, values.get(0), JDBCType.DATE);
        statement.setObject(2, values.get(1), JDBCType.CHAR);
        return statement.executeQuery();
    }

    private Record getCurrentRecord(ResultSet resultSet) throws SQLException {
        List<String> record = new LinkedList<>();
        Optional<Object> nomePortata = Optional.of(resultSet.getObject(1));
        record.addAll(List.of(nomePortata.get().toString()));
        return new RecordImpl(record);
    }
    
}
