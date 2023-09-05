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

public class FornitoriIngredienteRunner extends AbstractRestaurantQueryRunner {

    private static final List<String> RESULT_LIST = List.of("Partita IVA", "Nome Azienda");

    @Override
    protected String getQueryText() {
       return RestaurantQuery.VISUALIZZARE_FORNITORI_DI_INGREDIENTE.getQueryText();
    }

    @Override
    protected List<String> getColumnsNames() {
        return RESULT_LIST;
    }

    @Override
    protected ResultSet getResultSet(List<String> values, PreparedStatement statement) throws SQLException {
        statement.setObject(1, values.get(0), JDBCType.CHAR);
        return statement.executeQuery();
    }

    @Override
    protected Record getCurrentRecord(ResultSet resultSet) throws SQLException {
        List<String> rowData = new LinkedList<>();
        Object partitaIVA = resultSet.getObject(1);
        Object nomeAzienda = resultSet.getObject(2);
        rowData.addAll(List.of(partitaIVA.toString(), nomeAzienda.toString()));
        return new RecordImpl(rowData);
    }
    
}
