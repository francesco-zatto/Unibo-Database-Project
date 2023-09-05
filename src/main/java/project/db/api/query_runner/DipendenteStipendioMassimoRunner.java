package project.db.api.query_runner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import project.db.api.RestaurantQuery;
import project.query.Record;
import project.query.RecordImpl;
import project.query.Table;
import project.tableFactory.StaticTableFactory;

/**
 * Class to run view the Dipendente with highest salary.
 */
public class DipendenteStipendioMassimoRunner implements RestaurantQueryRunner {

    private static final List<String> RESULT_LIST = List.of("Codice", "Codice Fiscale", "Nome", "Cognome"); 

    /**
     * {@inheritDoc}
     */
    @Override
    public Table runQuery(Connection connection, List<String> values) throws SQLException {
        String queryString = RestaurantQuery.VISUALIZZARE_DIPENDENTE_STIPENDIO_MASSIMO.getQueryText();
        PreparedStatement statement = connection.prepareStatement(queryString);
        ResultSet resultSet = statement.executeQuery();
        List<Record> recordList = new LinkedList<>();
        recordList.add(new RecordImpl(RESULT_LIST));
        while (resultSet.next()) {
            recordList.add(getCurrentRecord(resultSet));
        }
        return StaticTableFactory.createTable(recordList);
    }

    private Record getCurrentRecord(ResultSet resultSet) throws SQLException {
        List<String> rowData = new LinkedList<>();
        Object codice = resultSet.getObject(1);
        Object CF = resultSet.getObject(2);
        Object nome = resultSet.getObject(3);
        Object cognome = resultSet.getObject(4);
        rowData.addAll(List.of(codice.toString(), CF.toString(), nome.toString(), cognome.toString()));
        return new RecordImpl(rowData);
    }
    
}
