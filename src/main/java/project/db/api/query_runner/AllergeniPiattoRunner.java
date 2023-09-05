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
import project.db.api.utilities.MetaDataQueries;
import project.query.Record;
import project.query.RecordImpl;
import project.query.Table;
import project.tableFactory.StaticTableFactory;

public class AllergeniPiattoRunner implements RestaurantQueryRunner {

    private static final List<String> RESULT_LIST = List.of("NomeIngrediente", "Descrizione");

    @Override
    public Table runQuery(Connection connection, List<String> values) throws SQLException {
        String queryString = RestaurantQuery.VISUALIZZARE_ALLERGENI_PIATTO.getQueryText();
        PreparedStatement statement = connection.prepareStatement(queryString);
        ResultSet resultSet = getResultSet(values, statement);
        List<Record> recordList = new LinkedList<>();
        recordList.add(new RecordImpl(RESULT_LIST));
        while (resultSet.next()) {
            recordList.add(getCurrentRecord(resultSet));
        }
        return StaticTableFactory.createTable(recordList);
    }

    private ResultSet getResultSet(List<String> values, PreparedStatement statement) throws SQLException {
        statement.setObject(1, values.get(0), JDBCType.CHAR);
        return statement.executeQuery();
    }

    private Record getCurrentRecord(ResultSet resultSet) throws SQLException {
        List<String> rowData = new LinkedList<>();
        Object nome = resultSet.getObject(1);
        Optional<Object> descrizione = Optional.ofNullable(resultSet.getObject(2));
        rowData.addAll(List.of(
            nome.toString(), 
            descrizione.isEmpty() ? MetaDataQueries.NULL_VALUE : descrizione.get().toString()
        ));
        return new RecordImpl(rowData);
    }
    
}
