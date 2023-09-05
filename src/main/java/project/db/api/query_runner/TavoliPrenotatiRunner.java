package project.db.api.query_runner;

import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import project.db.api.RestaurantQuery;
import project.query.Record;
import project.query.RecordImpl;

/**
 * Class to run to view booked Tavoli query.
 */
public class TavoliPrenotatiRunner extends AbstractRestaurantQueryRunner {

    private static final List<String> RESULT_LIST = List.of(
        "CodTavolo", 
        "NumeroInSala", 
        "NumeroPersone", 
        "NumeroSala"
    );

    @Override
    protected ResultSet getResultSet(List<String> values, PreparedStatement statement) throws SQLException {
        statement.setObject(1, values.get(0), JDBCType.INTEGER);
        statement.setObject(2, values.get(1), JDBCType.CHAR);
        statement.setObject(3, values.get(2), JDBCType.CHAR);
        return statement.executeQuery();
    }

    @Override
    protected Record getCurrentRecord(ResultSet resultSet) throws SQLException {
        List<String> rowData = new LinkedList<>();
        Object codTavolo = resultSet.getObject(1);
        Object numeroInSala = resultSet.getObject(2);
        Object numeroPersone = resultSet.getObject(3);
        Object numeroSala = resultSet.getObject(4);
        rowData.addAll(List.of(
            codTavolo.toString(), 
            numeroInSala.toString(),
            numeroPersone.toString(), 
            numeroSala.toString()
        ));
        return new RecordImpl(rowData);
    }

    @Override
    protected String getQueryText() {
        return RestaurantQuery.VISUALIZZARE_TAVOLI_PRENOTATI_IN_SALA_E_SLOT.getQueryText();
    }

    @Override
    protected List<String> getColumnsNames() {
        return RESULT_LIST;
    }
    
}
