package project.db.api.query_runner;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import project.db.api.RestaurantQuery;
import project.query.Record;
import project.query.RecordImpl;

/**
 * Class to run view the Dipendente with highest salary.
 */
public class DipendenteStipendioMassimoRunner extends AbstractRestaurantQueryRunner{

    private static final List<String> RESULT_LIST = List.of("Codice", "Codice Fiscale", "Nome", "Cognome");

    @Override
    protected Record getCurrentRecord(ResultSet resultSet) throws SQLException {
        List<String> rowData = new LinkedList<>();
        Object codice = resultSet.getObject(1);
        Object CF = resultSet.getObject(2);
        Object nome = resultSet.getObject(3);
        Object cognome = resultSet.getObject(4);
        rowData.addAll(List.of(codice.toString(), CF.toString(), nome.toString(), cognome.toString()));
        return new RecordImpl(rowData);
    }

    @Override
    protected String getQueryText() {
        return RestaurantQuery.VISUALIZZARE_DIPENDENTE_STIPENDIO_MASSIMO.getQueryText();
    }

    @Override
    protected List<String> getColumnsNames() {
        return RESULT_LIST;
    }

    @Override
    protected ResultSet getResultSet(List<String> values, PreparedStatement statement) throws SQLException {
        return statement.executeQuery();
    }
    
}
